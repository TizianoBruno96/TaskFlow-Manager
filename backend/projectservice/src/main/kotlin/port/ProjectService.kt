package port

import core.entity.Project
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

interface ProjectService {
    fun save(project: Project): Uni<Project>
    fun update(project: Project): Uni<Project>
    fun findById(id: String): Uni<Project?>
    fun findAll(): Multi<Project>
    fun delete(id: String): Uni<Boolean>
}