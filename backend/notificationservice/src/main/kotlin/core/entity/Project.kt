package core.entity

import org.bson.types.ObjectId
import java.util.*

data class Project(
    val name: String? = null,
    val description: String? = null,
    val createdBy: String? = null,
    val tasks: MutableList<ObjectId> = mutableListOf(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
)