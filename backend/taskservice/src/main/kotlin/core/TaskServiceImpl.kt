package core

import core.dto.TaskDTO
import core.entity.Task
import core.utils.TaskMapper
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import port.TaskEventService
import port.TaskRepository
import port.TaskService
import java.util.*

@ApplicationScoped
class TaskServiceImpl @Inject constructor(
    private val taskRepository: TaskRepository,
    private val taskEventService: TaskEventService,
) : TaskService {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private val taskMapper: TaskMapper = TaskMapper()

    override fun save(task: Task): Uni<Task> =
        Uni.createFrom().item(task)
            .invoke { _ -> logger.info("Saving task $task") }
            .invoke{ it -> logger.info("Task id: ${it.id}") }
            .map { val id = it.id ?: ObjectId.get()
                it.copy(id = id, createdAt = Date(), updatedAt = Date()) }
            .chain { it -> taskRepository.save(it) }
            .invoke { it -> logger.info("Task $it saved") }
            //.map { taskMapper.toDto(it) }
            .invoke { it -> taskEventService.emitTaskCreated(taskMapper.toDto(it)) }
            .onFailure().invoke { ex -> logger.error("Error saving task: $ex") }

    override fun update(taskDTO: TaskDTO): Uni<TaskDTO> =
        Uni.createFrom().item(taskMapper.toEntity(taskDTO))
            .invoke { _ -> logger.info("Updating task $taskDTO") }
            .map { val createdAt = it.createdAt ?: Date()
                it.copy(createdAt = createdAt, updatedAt = Date()) }
            .chain { it -> taskRepository.update(it) }
            .invoke { it -> logger.info("Task $it updated") }
            .map { taskMapper.toDto(it) }
            .invoke { it -> taskEventService.emitTaskUpdated(it) }
            .onFailure().invoke { ex -> logger.error("Error updating task: $ex") }

    override fun findById(id: ObjectId): Uni<TaskDTO?> =
        taskRepository.findById(id)
            .invoke { _ -> logger.info("Finding task with id $id") }
            .onItem().ifNull().failWith(NoSuchElementException("Task with id $id not found"))
            .map { taskMapper.toDto(it!!) }

    override fun findAll(): Multi<TaskDTO> =
        taskRepository.findAll()
            .invoke { tasks -> logger.info("Found tasks: $tasks") }
            .map { taskMapper.toDto(it) }


    override fun findByProjectId(projectId: ObjectId): Multi<TaskDTO> =
        taskRepository.findByProjectId(projectId)
            .invoke { _ -> logger.info("Finding tasks for project $projectId") }
            .map { taskMapper.toDto(it) }

    override fun delete(id: ObjectId): Uni<Boolean> =
        Uni.createFrom().item(id)
            .invoke { _ -> logger.info("Deleting task with id $id") }
            .chain { it -> taskRepository.delete(it) }
            .invoke { result ->
                if (result) logger.info("Task with id $id deleted")
                else logger.warn("Task with id $id not found")
            }
}