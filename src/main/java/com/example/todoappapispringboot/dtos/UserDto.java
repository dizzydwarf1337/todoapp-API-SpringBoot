package com.example.todoappapispringboot.dtos;

import com.example.todoappapispringboot.models.User;

import java.util.UUID;

public class UserDto {
    private String id;
    private String userName;
    private String token;


    public UserDto(User user, String token){
        this.id = user.getId().toString();
        this.userName = user.getUsername();
        this.token = token;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getToken() {
        return token;
    }
}
