package port

import core.dto.TaskDTO

interface TaskEventService {
    fun emitTaskCreated(task: TaskDTO)
    fun emitTaskUpdated(task: TaskDTO)
}