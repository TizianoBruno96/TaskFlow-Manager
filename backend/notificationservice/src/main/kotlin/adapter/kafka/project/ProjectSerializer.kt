package adapter.kafka.project

import core.dto.ProjectDTO
import io.quarkus.kafka.client.serialization.ObjectMapperSerializer

class ProjectSerializer : ObjectMapperSerializer<ProjectDTO>()