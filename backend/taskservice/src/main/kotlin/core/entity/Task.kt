package core.entity

import org.bson.types.ObjectId
import java.util.*

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val status: String,
    val assignedTo: String,
    val projectId: ObjectId,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
