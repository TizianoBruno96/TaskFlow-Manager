package adapter.kafka

import core.dto.TaskDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import org.eclipse.microprofile.reactive.messaging.Message
import org.slf4j.LoggerFactory
import port.TaskEventService

@ApplicationScoped
class KafkaTaskEvent @Inject constructor(
    @Channel("task-created") private val taskCreatedEmitter: Emitter<TaskDTO>,
    @Channel("task-updated") val taskUpdatedEmitter: Emitter<TaskDTO>
) : TaskEventService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun emitTaskCreated(task: TaskDTO) {
        val message: Message<TaskDTO> = Message.of(task)
        logger.info("Sending task created with title {}", task.title)
        taskCreatedEmitter.send(message)
    }

    override fun emitTaskUpdated(task: TaskDTO) {
        val message: Message<TaskDTO> = Message.of(task)
        logger.info("Sending task updated with title {}", task.title)
        taskUpdatedEmitter.send(message)
    }
}