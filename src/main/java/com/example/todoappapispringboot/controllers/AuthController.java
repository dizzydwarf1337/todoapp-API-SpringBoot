package com.example.todoappapispringboot.controllers;


import com.example.todoappapispringboot.dtos.ApiResponse;
import com.example.todoappapispringboot.dtos.Auth.LoginDto;
import com.example.todoappapispringboot.dtos.Auth.RegisterDto;
import com.example.todoappapispringboot.dtos.User.UserDto;
import com.example.todoappapispringboot.services.AuthService;
import com.example.todoappapispringboot.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController extends BaseController {
    private final AuthService authService;
    private final JwtService jwtService;
    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }
    @PostMapping("/public/login")
    public ResponseEntity<ApiResponse<UserDto>> login(@RequestBody LoginDto loginDto){
        var user = authService.authenticate(loginDto);
        if(user!=null){
            var token = jwtService.generateToken(user);
            if(token!=null) {
                UserDto userDto = new UserDto(user, token);
                return handleResponse(ApiResponse.success(userDto));
            }
            return handleResponse(ApiResponse.failure("Invalid username or password"));
        }
        return handleResponse(ApiResponse.failure("Invalid username or password"));
    }

    @PostMapping("/public/register")
    public ResponseEntity<ApiResponse<String>> signUp(@RequestBody RegisterDto registerDto){
        try{
            authService.signup(registerDto);
            return handleResponse(ApiResponse.success("Sign up successful"));
        }
        catch(Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }

}
