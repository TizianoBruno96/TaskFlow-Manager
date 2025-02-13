package port

import core.dto.TaskDTO
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

interface TaskService {
    fun save(taskDTO: TaskDTO): Uni<TaskDTO>
    fun update(taskDTO: TaskDTO): Uni<TaskDTO>
    fun findById(id: String): Uni<TaskDTO?>
    fun findByProjectId(projectId: String): Multi<TaskDTO>
    fun findAll(): Multi<TaskDTO>
    fun delete(id: String): Uni<Boolean>
}