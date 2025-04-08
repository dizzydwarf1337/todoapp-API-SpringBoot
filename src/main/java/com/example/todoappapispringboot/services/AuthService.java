package com.example.todoappapispringboot.services;

import com.example.todoappapispringboot.dtos.Auth.LoginDto;
import com.example.todoappapispringboot.dtos.Auth.RegisterDto;
import com.example.todoappapispringboot.models.User;
import com.example.todoappapispringboot.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User signup(RegisterDto input) {
        User user = new User();
        user.setUserName(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
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
