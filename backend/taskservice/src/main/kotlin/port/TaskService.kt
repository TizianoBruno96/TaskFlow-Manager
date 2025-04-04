package port

import core.dto.TaskDTO
import core.entity.Task
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import org.bson.types.ObjectId

interface TaskService {
    fun save(task: Task): Uni<Task>
    fun update(taskDTO: TaskDTO): Uni<TaskDTO>
    fun findById(id: ObjectId): Uni<TaskDTO?>
    fun findByProjectId(projectId: ObjectId): Multi<TaskDTO>
    fun findAll(): Multi<TaskDTO>
    fun delete(id: ObjectId): Uni<Boolean>
}