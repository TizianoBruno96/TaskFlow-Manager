package core.utils

import core.dto.ProjectDto
import core.entity.Project
import jakarta.inject.Singleton
import org.bson.types.ObjectId

@Singleton
class ProjectMapper {
    fun toDto(project: Project) = ProjectDto(
        name = project.name,
        description = project.description,
        participants = project.participants,
        createdBy = project.createdBy,
        createdAt = project.createdAt,
        updatedAt = project.updatedAt
    )

    fun toEntity(projectDto: ProjectDto) = Project(
        id = ObjectId(),
        name = projectDto.name,
        description = projectDto.description,
        participants = projectDto.participants,
        createdBy = projectDto.createdBy,
        createdAt = projectDto.createdAt,
        updatedAt = projectDto.updatedAt
    )
}