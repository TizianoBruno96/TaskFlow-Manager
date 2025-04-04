package port

import core.entity.Project
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import org.bson.types.ObjectId

interface ProjectRepository {
    fun save(project: Project): Uni<Project>
    fun update(project: Project): Uni<Project>
    fun findById(id: ObjectId): Uni<Project?>
    fun findAll(): Multi<Project>
    fun delete(id: ObjectId): Uni<Boolean>
}