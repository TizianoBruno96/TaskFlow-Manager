package adapter.controller

import core.dto.ProjectDTO
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import port.ProjectResourceService
import port.ProjectService

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class ProjectResource @Inject constructor(
    private val projectService: ProjectService
) : ProjectResourceService {

    @POST
    override fun save(@RequestBody projectDto: ProjectDTO): Uni<ProjectDTO> = projectService.save(projectDto)

    @PUT
    override fun update(@RequestBody projectDto: ProjectDTO): Uni<ProjectDTO> = projectService.update(projectDto)

    @GET
    @Path("{id}")
    override fun findById(@PathParam("id") id: String): Uni<ProjectDTO?> = projectService.findById(id)

    @GET
    override fun findAll(): Multi<ProjectDTO> = projectService.findAll()

    @DELETE
    @Path("{id}")
    override fun delete(@PathParam("id") id: String): Uni<Boolean> = projectService.delete(id)
}