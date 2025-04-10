package com.example.todoappapispringboot.services;


import com.example.todoappapispringboot.dtos.User.EditUserNameDto;
import com.example.todoappapispringboot.dtos.User.SimpleUserDto;
import com.example.todoappapispringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<SimpleUserDto> GetAllUsers(){
        List<SimpleUserDto> userDtos = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            userDtos.add(new SimpleUserDto(user));
        });
        return userDtos;
    }

    public void EditUserName(EditUserNameDto editUserNameDto){
        var user = userRepository.findById(UUID.fromString(editUserNameDto.getUserId())).orElseThrow(()->new RuntimeException("User not found"));
        user.setUserName(editUserNameDto.getUserName());
        userRepository.save(user);
    }

    public void DeleteUser(UUID userId){
        var user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        userRepository.delete(user);
    }


}
