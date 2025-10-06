package com.vibhas.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vibhas.taskmanager.model.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
