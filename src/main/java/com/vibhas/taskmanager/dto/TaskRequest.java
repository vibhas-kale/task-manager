package com.vibhas.taskmanager.dto;

import com.vibhas.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title can have at most 100 characters")
    private String title;

    @Size(max = 500, message = "Description can have at most 500 characters")
    private String description;

    @NotNull(message = "Status is mandatory")
    private String status;

    private LocalDateTime dueDate;
}
