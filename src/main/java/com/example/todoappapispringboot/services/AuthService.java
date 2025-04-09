package com.example.todoappapispringboot.services;

import com.example.todoappapispringboot.dtos.Auth.LoginDto;
import com.example.todoappapispringboot.dtos.Auth.RegisterDto;
import com.example.todoappapispringboot.models.Category;
import com.example.todoappapispringboot.models.Status;
import com.example.todoappapispringboot.models.Task;
import com.example.todoappapispringboot.models.User;
import com.example.todoappapispringboot.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void signup(RegisterDto input) {
        if (userRepository.existsByUserName(input.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUserName(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        Status newStatus = new Status();
        newStatus.setTitle("NEW");
        newStatus.setUser(user);

        Status inProgressStatus = new Status();
        inProgressStatus.setTitle("IN_PROGRESS");
        inProgressStatus.setUser(user);

        Status completedStatus = new Status();
        completedStatus.setTitle("COMPLETED");
        completedStatus.setUser(user);

        Category ImportantCategory = new Category();
        ImportantCategory.setTitle("Important");
        ImportantCategory.setUser(user);

        Category WorkCategory = new Category();
        WorkCategory.setTitle("Work");
        WorkCategory.setUser(user);

        Category OtherCategory = new Category();
        OtherCategory.setTitle("Other");
        OtherCategory.setUser(user);

        Task FirstTask = new Task();
        FirstTask.setTitle("First Task");
        FirstTask.setDescription("Your First Task");
        FirstTask.setUser(user);
        FirstTask.setStatus(newStatus);
        FirstTask.getCategories().add(OtherCategory);

        user.getStatuses().add(newStatus);
        user.getStatuses().add(inProgressStatus);
        user.getStatuses().add(completedStatus);
        user.getCategories().add(ImportantCategory);
        user.getCategories().add(WorkCategory);
        user.getCategories().add(OtherCategory);
        user.getTasks().add(FirstTask);
        userRepository.save(user);

    }

    public User authenticate(LoginDto input) {
        var result = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        if(result.isAuthenticated()){
            var user = userRepository.findByUserName(input.getUsername())
                    .orElseThrow();
            return user;
        }
        return null;
    }
}
