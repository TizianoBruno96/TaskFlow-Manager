package port

import core.dto.ProjectDTO
import core.dto.TaskDTO

interface NotificationService {
    fun sendTaskNotification(task: TaskDTO, eventType: String)
    fun sendProjectNotification(project: ProjectDTO, eventType: String)
}