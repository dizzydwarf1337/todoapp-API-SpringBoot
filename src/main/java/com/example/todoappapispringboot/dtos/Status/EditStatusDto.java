package com.example.todoappapispringboot.dtos.Status;

public class EditStatusDto {
    private String statusId;
    private String title;

    public String getStatusId() {
        return statusId;
    }
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
