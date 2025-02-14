package port

import core.dto.ProjectDto
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

interface ProjectResourceService {
    fun save(projectDto: ProjectDto): Uni<ProjectDto>
    fun update(projectDto: ProjectDto): Uni<ProjectDto>
    fun findById(id: String): Uni<ProjectDto?>
    fun findAll(): Multi<ProjectDto>
    fun delete(id: String): Uni<Boolean>
}