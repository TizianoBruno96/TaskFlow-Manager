package adapter.api

import core.dto.TaskDTO
import core.entity.Task
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.bson.types.ObjectId
import port.TaskResourceService
import port.TaskService

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class TaskResource @Inject constructor(
    private val taskService: TaskService
) : TaskResourceService {

    @POST
    override fun save(task: Task): Uni<Task> = taskService.save(task)

    @PUT
    override fun update(taskDTO: TaskDTO): Uni<TaskDTO> = taskService.update(taskDTO)

    @GET
    @Path("{id}")
    override fun findById(@PathParam("id") id: String): Uni<TaskDTO?> = taskService.findById(id)

    @GET
    @Path("/project/{id}")
    override fun findByProjectId(@PathParam("id") projectId: String): Multi<TaskDTO> =
        taskService.findByProjectId(projectId)

    @GET
    override fun findAll(): Multi<TaskDTO> = taskService.findAll()

    @DELETE
    override fun delete(id: String): Uni<Boolean> = taskService.delete(id)
}