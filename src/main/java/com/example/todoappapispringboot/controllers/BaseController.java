package com.example.todoappapispringboot.controllers;

import com.example.todoappapispringboot.dtos.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    protected <T> ResponseEntity<ApiResponse<T>> handleResponse(ApiResponse<T> result) {
        if (!result.getIsSuccess()) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }
}