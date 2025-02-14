package adapter

import adapter.websocket.NotificationServiceImpl
import core.dto.ProjectDTO
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Message
import org.slf4j.LoggerFactory
import port.KafkaProjectEventService
import java.util.concurrent.CompletionStage

@ApplicationScoped
class KafkaProjectEvent @Inject constructor(
    private val notificationServiceImpl: NotificationServiceImpl
) : KafkaProjectEventService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Incoming("project-created")
    override fun projectCreatedConsumer(message: Message<ProjectDTO>): Uni<CompletionStage<Void>> =
        Uni.createFrom().item(message.payload)
            .onItem().ifNull().failWith(IllegalArgumentException("Project is null"))
            .invoke { project ->
                logger.info("Received project created message: $project")
                notificationServiceImpl.sendProjectNotification(project, "project-created")
            }
            .onFailure().invoke { failure ->
                logger.error("Error processing project created message: $failure")
                message.nack(failure)
            }
            .map { message.ack() }
            .onFailure().recoverWithNull()

    @Incoming("project-updated")
    override fun projectUpdatedConsumer(message: Message<ProjectDTO>): Uni<CompletionStage<Void>> =
        Uni.createFrom().item(message.payload)
            .onItem().ifNull().failWith(IllegalArgumentException("Project is null"))
            .invoke { project ->
                logger.info("Received project updated message: $project")
                notificationServiceImpl.sendProjectNotification(project, "project-updated")
            }
            .onFailure().invoke { failure ->
                logger.error("Error processing project updated message: $failure")
                message.nack(failure)
            }
            .map { message.ack() }
            .onFailure().recoverWithNull()
}