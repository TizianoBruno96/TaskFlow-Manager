package port

import core.dto.ProjectDTO
import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.reactive.messaging.Message
import java.util.concurrent.CompletionStage

interface KafkaProjectEventService {
    fun projectCreatedConsumer(message: Message<ProjectDTO>): Uni<CompletionStage<Void>>
    fun projectUpdatedConsumer(message: Message<ProjectDTO>): Uni<CompletionStage<Void>>
}