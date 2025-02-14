package core.dto

import java.util.*

data class ProjectDto (
    val name: String?,
    var description: String?,
    var participants: List<String>?,
    var createdBy: String? = null,
    var createdAt: Date? = Date(),
    var updatedAt: Date? = Date(),
)