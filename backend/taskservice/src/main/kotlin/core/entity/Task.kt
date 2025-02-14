package core.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import java.util.*

data class Task(
    @BsonId var id: ObjectId? = ObjectId(),
    @BsonProperty("title") var title: String? = null,
    @BsonProperty("description") var description: String? = null,
    @BsonProperty("status") var status: String? = null,
    @BsonProperty("assignedTo") var assignedTo: String? = null,
    @BsonProperty("projectId") var projectId: ObjectId? = null,
    @BsonProperty("createdAt") var createdAt: Date? = Date(),
    @BsonProperty("updatedAt") var updatedAt: Date? = Date()
) {
    init {
        if (id == null) {
            id = ObjectId()
        }
    }
}
