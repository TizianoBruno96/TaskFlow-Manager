package adapter.repository

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Indexes
import core.entity.Project
import io.quarkus.mongodb.reactive.ReactiveMongoClient
import io.quarkus.mongodb.reactive.ReactiveMongoCollection
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.LoggerFactory
import port.ProjectRepository

@ApplicationScoped
class MongoProjectRepository @Inject constructor(
    mongoClient: ReactiveMongoClient,
    @ConfigProperty(name = "mongodb.collection.projects") val collectionName: String
) : ProjectRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private lateinit var collection: ReactiveMongoCollection<Project>

    companion object {
        private val FIELD_ID = Project::id.name
        private const val INDEX_ID = "IndexId"
    }

    init {
        val database = mongoClient.getDatabase("myDb")
        collection = database.getCollection(collectionName, Project::class.java)

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

    override fun save(project: Project): Uni<Project> =
        collection.insertOne(project).replaceWith(project)

    override fun update(project: Project): Uni<Project> =
        collection.replaceOne(eq(FIELD_ID, project.id), project).replaceWith(project)

    override fun findById(id: String): Uni<Project?> =
        collection.find(eq(FIELD_ID, id)).toUni()

    override fun findAll(): Multi<Project> =
        collection.find()

    override fun delete(id: String): Uni<Boolean> =
        collection.deleteOne(eq(FIELD_ID, id)).map { it.deletedCount > 0 }
}