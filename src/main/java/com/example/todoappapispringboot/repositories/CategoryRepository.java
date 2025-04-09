package com.example.todoappapispringboot.repositories;

import com.example.todoappapispringboot.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<List<Category>> findByUserId(UUID userId);
}
