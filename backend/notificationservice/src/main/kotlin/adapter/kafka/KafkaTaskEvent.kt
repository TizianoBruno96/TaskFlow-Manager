package adapter.kafka

import adapter.websocket.NotificationServiceImpl
import core.dto.TaskDTO
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Message
import org.slf4j.LoggerFactory
import port.KafkaTaskEventService
import java.util.concurrent.CompletionStage

@ApplicationScoped
class KafkaTaskEvent @Inject constructor(
    private val notificationServiceImpl: NotificationServiceImpl
) : KafkaTaskEventService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Incoming("task-created")
    override fun taskCreatedConsumer(message: Message<TaskDTO>): Uni<CompletionStage<Void>> =
        Uni.createFrom().item(message.payload)
            .onItem().ifNull().failWith(IllegalArgumentException("Task is null"))
            .invoke { task ->
                logger.info("Received task created message: $task")
                notificationServiceImpl.sendTaskNotification(task, "task-created")
            }
            .onFailure().invoke { failure ->
                logger.error("Error processing task created message: $failure")
                message.nack(failure)
            }
            .map { message.ack() }
            .onFailure().recoverWithNull()

    @Incoming("task-updated")
    override fun taskUpdatedConsumer(message: Message<TaskDTO>): Uni<CompletionStage<Void>> =
        Uni.createFrom().item(message.payload)
            .onItem().ifNull().failWith(IllegalArgumentException("Task is null"))
            .invoke { task ->
                logger.info("Received task updated message: $task")
                notificationServiceImpl.sendTaskNotification(task, "task-updated")
            }
            .onFailure().invoke { failure ->
                logger.error("Error processing task updated message: $failure")
                message.nack(failure)
            }
            .map { message.ack() }
            .onFailure().recoverWithNull()
}