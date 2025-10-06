package com.vibhas.taskmanager.controller;


import com.vibhas.taskmanager.dto.TaskRequest;
import com.vibhas.taskmanager.dto.TaskResponse;
import com.vibhas.taskmanager.mapper.TaskMapper;
import com.vibhas.taskmanager.model.Task;
import com.vibhas.taskmanager.model.TaskStatus;
import com.vibhas.taskmanager.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService taskService;

    @MockitoBean
    private TaskMapper taskMapper;

    @Test
    void testCreateTask() throws Exception {
        TaskRequest request = new TaskRequest();
        request.setTitle("Test Task");
        request.setDescription("Description");
        request.setStatus("PENDING");

        Task taskEntity = new Task();
        taskEntity.setTitle(request.getTitle());
        taskEntity.setDescription(request.getDescription());
        taskEntity.setStatus(TaskStatus.PENDING);
        taskEntity.setId(1L);
        taskEntity.setCreatedAt(LocalDateTime.now());

        TaskResponse response = new TaskResponse();
        response.setId(1L);
        response.setTitle("Test Task");
        response.setDescription("Description");
        response.setStatus(String.valueOf(TaskStatus.PENDING));
        response.setCreatedAt(taskEntity.getCreatedAt());

        Mockito.when(taskMapper.toEntity(any(TaskRequest.class))).thenReturn(taskEntity);
        Mockito.when(taskService.createTask(any(Task.class))).thenReturn(taskEntity);
        Mockito.when(taskMapper.toDto(any(Task.class))).thenReturn(response);

        mockMvc.perform(post("/operation/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void testGetTaskById() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setStatus(TaskStatus.PENDING);

        TaskResponse response = new TaskResponse();
        response.setId(1L);
        response.setTitle("Task 1");
        response.setStatus(String.valueOf(TaskStatus.PENDING));

        Mockito.when(taskService.getTaskById(1L)).thenReturn(Optional.of(task));
        Mockito.when(taskMapper.toDto(task)).thenReturn(response);

        mockMvc.perform(get("/operation/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Task 1"));
    }

    @Test
    void testGetAllTasks() throws Exception {
        Task t1 = new Task(); t1.setId(1L); t1.setTitle("T1"); t1.setStatus(TaskStatus.PENDING);
        Task t2 = new Task(); t2.setId(2L); t2.setTitle("T2"); t2.setStatus(TaskStatus.IN_PROGRESS);

        TaskResponse r1 = new TaskResponse(); r1.setId(1L); r1.setTitle("T1"); r1.setStatus(String.valueOf(TaskStatus.PENDING));
        TaskResponse r2 = new TaskResponse(); r2.setId(2L); r2.setTitle("T2"); r2.setStatus(String.valueOf(TaskStatus.IN_PROGRESS));

        Mockito.when(taskService.getAllTasks()).thenReturn(Arrays.asList(t1, t2));
        Mockito.when(taskMapper.toDto(t1)).thenReturn(r1);
        Mockito.when(taskMapper.toDto(t2)).thenReturn(r2);

        mockMvc.perform(get("/operation/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("T1"))
                .andExpect(jsonPath("$[1].status").value("IN_PROGRESS"));
    }

    @Test
    void testDeleteTask() throws Exception {
        Mockito.doNothing().when(taskService).deleteTask(anyLong());

        mockMvc.perform(delete("/operation/tasks/1"))
                .andExpect(status().isNoContent());
    }
}
