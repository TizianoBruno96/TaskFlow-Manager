package port

import core.entity.Task
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import org.bson.types.ObjectId

interface TaskRepository {
    fun save(task: Task): Uni<Task>
    fun update(task: Task): Uni<Task>
    fun findById(id: ObjectId): Uni<Task?>
    fun findByProjectId(projectId: ObjectId): Multi<Task>
    fun findAll(): Multi<Task>
    fun delete(id: ObjectId): Uni<Boolean>
}