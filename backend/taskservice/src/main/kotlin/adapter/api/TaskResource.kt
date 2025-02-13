package adapter.api

import core.dto.TaskDTO
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import port.TaskResourceService
import port.TaskService

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TaskResource @Inject constructor(
    private val taskService: TaskService
): TaskResourceService{

    @POST
    override fun save(taskDTO: TaskDTO): Uni<TaskDTO> = taskService.save(taskDTO)

    @PUT
    override fun update(taskDTO: TaskDTO): Uni<TaskDTO> = taskService.update(taskDTO)

    @GET
    @Path("{id}")
    override fun findById(@PathParam("id") id: String): Uni<TaskDTO?> = taskService.findById(id)

    @GET
    @Path("/project/{id}")
    override fun findByProjectId(@PathParam("id") projectId: String): Multi<TaskDTO> = taskService.findByProjectId(projectId)

    @DELETE
    override fun delete(id: String): Uni<Boolean> = taskService.delete(id)
}