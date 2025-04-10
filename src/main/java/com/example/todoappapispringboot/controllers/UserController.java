package com.example.todoappapispringboot.controllers;


import com.example.todoappapispringboot.dtos.ApiResponse;
import com.example.todoappapispringboot.dtos.User.SimpleUserDto;
import com.example.todoappapispringboot.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController extends BaseController{

    private final UserService userService;

    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SimpleUserDto>>> GetAllUsers(){
        try{
            var users = userService.GetAllUsers();
            return handleResponse(ApiResponse.success(users));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<String>> DeleteUser(@PathVariable String userId){
        try{
            userService.DeleteUser(UUID.fromString(userId));
            return handleResponse(ApiResponse.success("User deleted successfully"));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }


}
