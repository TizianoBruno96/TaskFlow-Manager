package core.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import java.util.*

data class ProjectDTO(
    val name: String?,
    var description: String?,
    var participants: List<String>?,
    var createdBy: String? = null,
    @JsonProperty(access = READ_ONLY) var createdAt: Date? = Date(),
    @JsonProperty(access = READ_ONLY) var updatedAt: Date? = Date(),
)