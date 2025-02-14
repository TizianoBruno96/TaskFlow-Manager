package adapter.kafka.project

import core.dto.ProjectDTO
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer

class ProjectDeserializer : ObjectMapperDeserializer<ProjectDTO>(ProjectDTO::class.java)