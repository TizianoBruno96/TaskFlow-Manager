package adapter.repository

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Indexes
import core.entity.Task
import io.quarkus.mongodb.reactive.ReactiveMongoClient
import io.quarkus.mongodb.reactive.ReactiveMongoCollection
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.bson.types.ObjectId
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.LoggerFactory
import port.TaskRepository

@ApplicationScoped
class MongoTaskRepository @Inject constructor(
    mongoClient: ReactiveMongoClient,
    @ConfigProperty(name = "mongodb.collection.tasks") val collectionName: String
) : TaskRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private lateinit var collection: ReactiveMongoCollection<Task>

    companion object {
        private val FIELD_ID = Task::id.name
        private val FIELD_PROJECT_ID = Task::projectId.name
        private const val INDEX_ID = "IndexId"
    }

    init {
        val database = mongoClient.getDatabase("myDb")
        collection = database.getCollection(collectionName, Task::class.java)

        Uni.createFrom().voidItem()
            .chain { ->
                collection.createIndex(
                    Indexes.ascending(FIELD_ID),
                    IndexOptions()
                        .unique(true)
                        .name(INDEX_ID)
                ).invoke { it -> logger.debug("Created index $it") }
                    .onFailure().invoke { ex -> logger.warn("Error creating index {}", INDEX_ID, ex) }
                    .onFailure().recoverWithNull()
            }.subscribe().with {
                logger.debug("Initialized collection $collectionName")
            }
    }

    override fun save(task: Task): Uni<Task> =
        collection.insertOne(task).replaceWith(task)

    override fun update(task: Task): Uni<Task> =
        collection.replaceOne(eq(FIELD_ID, task.id), task).replaceWith(task)

    override fun findById(id: ObjectId): Uni<Task?> =
        collection.find(eq(FIELD_ID, id)).toUni()

    override fun findByProjectId(projectId: ObjectId): Multi<Task> =
        collection.find(eq(FIELD_PROJECT_ID, projectId))

    override fun findAll(): Multi<Task> =
        collection.find()

    override fun delete(id: ObjectId): Uni<Boolean> =
        collection.deleteOne(eq(FIELD_ID, id)).map { it.deletedCount > 0 }
}