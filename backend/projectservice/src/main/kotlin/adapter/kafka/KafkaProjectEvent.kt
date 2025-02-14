package adapter.kafka

import core.dto.ProjectDTO
import io.vertx.core.impl.logging.LoggerFactory
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import port.ProjectEventService

@ApplicationScoped
class KafkaProjectEvent @Inject constructor(
    @Channel("project-created") private val projectCreatedEmitter: Emitter<ProjectDTO>,
    @Channel("project-updated") private val projectUpdatedEmitter: Emitter<ProjectDTO>
) : ProjectEventService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun emitProjectCreated(projectDto: ProjectDTO) {
        val message: ProjectDTO = projectDto
        logger.info("Sending creation of project with name ${projectDto.name} created")
        projectCreatedEmitter.send(message)
    }

    override fun emitProjectUpdated(projectDto: ProjectDTO) {
        val message: ProjectDTO = projectDto
        logger.info("Sending update of project with name ${projectDto.name} updated")
        projectUpdatedEmitter.send(message)
    }
}