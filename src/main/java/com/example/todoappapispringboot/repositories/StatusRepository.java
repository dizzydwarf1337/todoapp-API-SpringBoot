package com.example.todoappapispringboot.repositories;

import com.example.todoappapispringboot.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<Status, UUID> {
    public List<Status> GetStatusesByUserId(UUID userId);
}
