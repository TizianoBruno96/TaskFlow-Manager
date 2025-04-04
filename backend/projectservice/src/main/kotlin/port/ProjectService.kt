package port

import core.dto.ProjectDTO
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import org.bson.types.ObjectId

interface ProjectService {
    fun save(projectDto: ProjectDTO): Uni<ProjectDTO>
    fun update(projectDto: ProjectDTO): Uni<ProjectDTO>
    fun findById(id: ObjectId): Uni<ProjectDTO?>
    fun findAll(): Multi<ProjectDTO>
    fun delete(id: ObjectId): Uni<Boolean>
}