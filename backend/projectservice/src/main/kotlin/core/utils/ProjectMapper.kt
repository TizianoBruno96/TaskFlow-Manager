package core.utils

import core.dto.ProjectDTO
import core.entity.Project
import jakarta.inject.Singleton
import org.bson.types.ObjectId

@Singleton
class ProjectMapper {
    fun toDto(project: Project) = ProjectDTO(
        name = project.name,
        description = project.description,
        participants = project.participants,
        createdBy = project.createdBy,
        createdAt = project.createdAt,
        updatedAt = project.updatedAt
    )

    fun toEntity(projectDto: ProjectDTO) = Project(
        name = projectDto.name,
        description = projectDto.description,
        participants = projectDto.participants,
        createdBy = projectDto.createdBy,
        createdAt = projectDto.createdAt,
        updatedAt = projectDto.updatedAt
    )
}