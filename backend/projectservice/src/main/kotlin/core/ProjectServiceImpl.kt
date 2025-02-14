package core

import core.entity.Project
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.slf4j.LoggerFactory
import port.ProjectRepository
import port.ProjectService

@ApplicationScoped
class ProjectServiceImpl @Inject constructor(
    private val projectRepository: ProjectRepository
) : ProjectService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun save(project: Project): Uni<Project> {
        TODO("Not yet implemented")
    }

    override fun update(project: Project): Uni<Project> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Uni<Project?> {
        TODO("Not yet implemented")
    }

    override fun findAll(): Multi<Project> {
        TODO("Not yet implemented")
    }

    override fun delete(id: String): Uni<Boolean> {
        TODO("Not yet implemented")
    }
}