package com.example.todoappapispringboot.dtos.Status;

public class CreateStatusDto {

    private String title;
    private String userId;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
