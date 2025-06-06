package com.example.todoappapispringboot.repositories;

import com.example.todoappapispringboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String username);

    boolean existsByUserName(String username);
}
