package core.utils

import core.dto.TaskDTO
import core.entity.Task
import jakarta.inject.Singleton
import org.bson.types.ObjectId

@Singleton
class TaskMapper {
    fun toDto(task: Task): TaskDTO = TaskDTO(
            title = task.title,
            description = task.description,
            status = task.status,
            assignedTo = task.assignedTo,
            projectId = task.projectId,
            createdAt = task.createdAt,
            updatedAt = task.updatedAt
        )


    fun toEntity(taskDTO: TaskDTO): Task = Task(
            id = ObjectId(),
            title = taskDTO.title,
            description = taskDTO.description,
            status = taskDTO.status,
            assignedTo = taskDTO.assignedTo,
            projectId = taskDTO.projectId,
            createdAt = taskDTO.createdAt,
            updatedAt = taskDTO.updatedAt
        )
}