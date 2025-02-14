package adapter.websocket

import core.dto.TaskDTO
import io.vertx.core.impl.logging.LoggerFactory
import jakarta.enterprise.context.ApplicationScoped
import port.NotificationService

@ApplicationScoped
class NotificationServiceImpl: NotificationService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun sendTaskNotification(task: TaskDTO, eventType: String) {
        //TODO Nella versione attuale, solo log per test
        logger.info("Sending notification: EventType=$eventType, Task=$task")

        //TODO Futuro: invio notifiche al frontend con WebSocket o altri sistemi
    }

}