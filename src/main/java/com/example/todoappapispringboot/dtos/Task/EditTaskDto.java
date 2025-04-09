package com.example.todoappapispringboot.dtos.Task;

import java.util.List;

public class EditTaskDto {

    private String taskId;
    private String title;
    private String description;
    private String statusId;
    private List<String> categoriesId;
    public String getTitle() {
        return title;
    }


    public String getTaskId(){
        return taskId;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public List<String> getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(List<String> categoriesId) {
        this.categoriesId = categoriesId;
    }

}
