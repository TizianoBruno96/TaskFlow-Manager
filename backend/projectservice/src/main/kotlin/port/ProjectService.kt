package port

import core.dto.ProjectDTO
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

interface ProjectService {
    fun save(projectDto: ProjectDTO): Uni<ProjectDTO>
    fun update(projectDto: ProjectDTO): Uni<ProjectDTO>
    fun findById(id: String): Uni<ProjectDTO?>
    fun findAll(): Multi<ProjectDTO>
    fun delete(id: String): Uni<Boolean>
}