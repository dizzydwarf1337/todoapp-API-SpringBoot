package com.example.todoappapispringboot.dtos;
public class ApiResponse<T> {

    private boolean isSuccess;
    private T value;
    private String error;

    public static <T> ApiResponse<T> success(T value) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setValue(value);
        return response;
    }

    public static <T> ApiResponse<T> failure(String error) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setError(error);
        return response;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
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
