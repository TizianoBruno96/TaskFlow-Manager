package core.utils

import core.dto.TaskDTO
import core.entity.Task
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface TaskMapper {
    companion object {
        val INSTANCE: TaskMapper = Mappers.getMapper(TaskMapper::class.java)
    }

    fun toDto(task: Task): TaskDTO
    fun toEntity(taskDto: TaskDTO): Task
}