package com.example.todoappapispringboot.controllers;


import com.example.todoappapispringboot.dtos.Auth.LoginDto;
import com.example.todoappapispringboot.dtos.Auth.RegisterDto;
import com.example.todoappapispringboot.dtos.UserDto;
import com.example.todoappapispringboot.services.AuthService;
import com.example.todoappapispringboot.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }
    @PostMapping("/public/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto){
        var user = authService.authenticate(loginDto);
        if(user!=null){
            var token = jwtService.generateToken(user);
            if(token!=null) {
                UserDto userDto = new UserDto(user, token);
                return ResponseEntity.ok(userDto);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/public/register")
    public ResponseEntity<String> signUp(@RequestBody RegisterDto registerDto){
        try{
            authService.signup(registerDto);
            return ResponseEntity.ok("Sign up successful");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
