package com.example.todoappapispringboot.repositories;

import com.example.todoappapispringboot.models.Category;
import com.example.todoappapispringboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> GetCategoriesByUserId(UUID userId);
}
