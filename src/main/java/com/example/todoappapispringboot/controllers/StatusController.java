package com.example.todoappapispringboot.controllers;

import com.example.todoappapispringboot.dtos.Status.StatusDto;
import com.example.todoappapispringboot.services.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/statuses")
public class StatusController {

    public final StatusService statusService;
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<StatusDto>> GetStatusesByUserId(String userId) {
        try{
            var statuses = statusService.GetStatusesByUserId(userId);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
