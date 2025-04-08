package com.example.todoappapispringboot.dtos.Status;

import com.example.todoappapispringboot.models.Status;

public class StatusDto {

    private String id;
    private String title;
    public StatusDto(Status status) {
        this.setId(status.getId().toString());
        this.setTitle(status.getTitle());
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
