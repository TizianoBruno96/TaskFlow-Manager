package core.dto

import org.bson.types.ObjectId
import java.util.*

data class TaskDTO(
    val title: String?,
    val description: String?,
    val status: String?,
    val assignedTo: String?,
    val projectId: ObjectId?,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)