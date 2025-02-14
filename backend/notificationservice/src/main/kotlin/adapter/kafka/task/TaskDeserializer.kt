package adapter.kafka.task

import core.dto.TaskDTO
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer

class TaskDeserializer : ObjectMapperDeserializer<TaskDTO>(TaskDTO::class.java)