package core

import core.dto.ProjectDTO
import core.utils.ProjectMapper
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import port.ProjectEventService
import port.ProjectRepository
import port.ProjectService
import java.util.*

@ApplicationScoped
class ProjectServiceImpl @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val projectEventService: ProjectEventService,
    private val mapper: ProjectMapper
) : ProjectService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun save(projectDto: ProjectDTO): Uni<ProjectDTO> =
        Uni.createFrom().item(mapper.toEntity(projectDto))
            .map { val id = it.id ?: ObjectId.get()
                it.copy(id = id, createdAt = Date(), updatedAt = Date()) }
            .invoke { it -> logger.info("Saving object $it") }
            .chain { it -> projectRepository.save(it) }
            .map { mapper.toDto(it) }
            .invoke { it -> projectEventService.emitProjectCreated(it) }
            .invoke { it -> logger.info("Object $it saved") }
            .onFailure().invoke { ex -> logger.error("Error saving object: $ex") }

    override fun update(projectDto: ProjectDTO): Uni<ProjectDTO> =
        Uni.createFrom().item(mapper.toEntity(projectDto))
            .invoke { _ -> logger.info("Updating object $projectDto") }
            .map { val createdAt = it.createdAt ?: Date()
                it.copy(createdAt = createdAt, updatedAt = Date()) }
            .chain { it -> projectRepository.update(it) }
            .map { mapper.toDto(it) }
            .invoke { it -> projectEventService.emitProjectUpdated(it) }
            .invoke { it -> logger.info("Object $it updated") }
            .onFailure().invoke { ex -> logger.error("Error updating object: $ex") }

    override fun findById(id: ObjectId): Uni<ProjectDTO?> =
        projectRepository.findById(id)
            .invoke { _ -> logger.info("Finding object with id $id") }
            .onItem().ifNull().failWith(NoSuchElementException("Object with id $id not found"))
            .invoke { it -> logger.info("Found object $it") }
            .map { mapper.toDto(it!!) }

    override fun findAll(): Multi<ProjectDTO> =
        projectRepository.findAll()
            .invoke { _ -> logger.info("Finding all projects") }
            .map { mapper.toDto(it) }

    override fun delete(id: ObjectId): Uni<Boolean> =
        Uni.createFrom().item(id)
            .invoke { _ -> logger.info("Deleting object with id $id") }
            .chain { _ -> projectRepository.delete(id) }
            .invoke { result ->
                if (result) logger.info("Object with id $id deleted")
                else logger.warn("Object with id $id not found")
            }
}