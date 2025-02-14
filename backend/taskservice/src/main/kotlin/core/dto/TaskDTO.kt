package core.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import java.util.*

data class TaskDTO(
    val title: String?,
    val description: String?,
    val status: String?,
    val assignedTo: String?,
    val projectId: String?,
    @JsonProperty(access = READ_ONLY) val createdAt: Date? = Date(),
    @JsonProperty(access = READ_ONLY) val updatedAt: Date? = Date()
)