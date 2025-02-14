package port

import core.dto.TaskDTO

interface NotificationService {
    fun sendTaskNotification(task: TaskDTO, eventType: String)
}