package core.dto

import java.util.*

data class TaskDTO(
    val title: String?,
    val description: String?,
    val status: String?,
    val assignedTo: String?,
    val projectId: String?,
    val createdAt: Date? = Date(),
    val updatedAt: Date? = Date()
)