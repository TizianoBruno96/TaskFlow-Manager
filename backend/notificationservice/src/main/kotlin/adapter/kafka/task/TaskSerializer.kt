package adapter.kafka.task

import core.dto.TaskDTO
import io.quarkus.kafka.client.serialization.ObjectMapperSerializer

class TaskSerializer: ObjectMapperSerializer<TaskDTO>()