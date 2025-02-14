package port

import core.dto.ProjectDTO

interface ProjectEventService {
    fun emitProjectCreated(projectDto: ProjectDTO)
    fun emitProjectUpdated(projectDto: ProjectDTO)
}