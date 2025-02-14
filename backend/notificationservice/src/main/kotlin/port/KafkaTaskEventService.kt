package port

import core.dto.TaskDTO
import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.reactive.messaging.Message
import java.util.concurrent.CompletionStage

interface KafkaTaskEventService {
    fun taskCreatedConsumer(message: Message<TaskDTO>): Uni<CompletionStage<Void>>
    fun taskUpdatedConsumer(message: Message<TaskDTO>): Uni<CompletionStage<Void>>
}