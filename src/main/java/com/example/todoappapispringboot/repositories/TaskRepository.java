package com.example.todoappapispringboot.repositories;

import com.example.todoappapispringboot.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository  extends JpaRepository<Task, UUID> {
    Optional<List<Task>> findByUserId(UUID userId);
}
