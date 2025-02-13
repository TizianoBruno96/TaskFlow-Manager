package port

import core.entity.Task
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

interface TaskRepository {
    fun save(task: Task): Uni<Task>
    fun update(task: Task): Uni<Task>
    fun findById(id: String): Uni<Task?>
    fun findByProjectId(projectId: String): Multi<Task>
    fun delete(id: String): Uni<Boolean>
}