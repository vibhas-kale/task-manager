package com.vibhas.taskmanager.mapper;

//Internal
import com.vibhas.taskmanager.dto.TaskRequest;
import com.vibhas.taskmanager.dto.TaskResponse;
import com.vibhas.taskmanager.model.Task;

//External
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(TaskRequest dto);

    // Map Task entity to TaskResponse
    TaskResponse toDto(Task task);
}
