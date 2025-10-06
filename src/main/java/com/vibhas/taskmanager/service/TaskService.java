package com.vibhas.taskmanager.service;

import com.vibhas.taskmanager.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Long id,Task task);
    void deleteTask(Long id);
    Optional<Task> getTaskById(Long id);
    List<Task> getAllTasks();
}


