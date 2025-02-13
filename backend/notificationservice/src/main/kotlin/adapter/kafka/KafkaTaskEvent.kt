package adapter.kafka

import core.dto.TaskDTO
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Message
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletionStage

@ApplicationScoped
class KafkaTaskEvent {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Incoming("task-created")
    fun taskCreatedConsumer(message: Message<TaskDTO>): CompletionStage<Void> {
        logger.info("Received task created message: ${message.payload}")
        return message.ack()
    }

    @Incoming("task-updated")
    fun taskUpdatedConsumer(message: Message<TaskDTO>): CompletionStage<Void> {
        logger.info("Received task updated message: ${message.payload}")
        return message.ack()
    }
}