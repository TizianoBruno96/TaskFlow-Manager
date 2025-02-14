package core.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import java.util.*

data class Project(
    @BsonId var id: String? = null,
    @BsonProperty("name") var name: String? = null,
    @BsonProperty("description") var description: String? = null,
    @BsonProperty("participants") var participants: List<String>? = emptyList(),
    @BsonProperty("createdBy") var createdBy: String? = null,
    @BsonProperty("createdAt") var createdAt: Date? = Date(),
    @BsonProperty("updatedAt") var updatedAt: Date? = Date(),
)