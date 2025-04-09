package com.example.todoappapispringboot.repositories;

import com.example.todoappapispringboot.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<Status, UUID> {
    Optional<List<Status>> findByUserId(UUID userId);
}
