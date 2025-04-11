package com.example.todoappapispringboot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private boolean isSuccess;
    private T value;
    private String error;

    public static <T> ApiResponse<T> success(T value) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setIsSuccess(true);
        response.setValue(value);
        return response;
    }

    public static <T> ApiResponse<T> failure(String error) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setIsSuccess(false);
        response.setError(error);
        return response;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
