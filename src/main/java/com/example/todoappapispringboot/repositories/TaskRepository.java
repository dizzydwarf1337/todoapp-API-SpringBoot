package com.example.todoappapispringboot.repositories;

import com.example.todoappapispringboot.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository  extends JpaRepository<Task, UUID> {
}
