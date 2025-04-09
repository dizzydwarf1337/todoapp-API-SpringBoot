package com.example.todoappapispringboot.dtos.Task;

import com.example.todoappapispringboot.models.Category;
import com.example.todoappapispringboot.models.Task;

import java.util.List;

public class TaskDto {

    private String taskId;
    private String title;
    private String description;
    private List<String> categoriesId;
    private String statusId;

    public TaskDto(Task task){
        taskId = task.getId().toString();
        this.title = task.getTitle();
        this.description = task.getDescription();
        categoriesId = task.getCategories().stream()
                .map(category -> category.getId().toString())
                .toList();
        statusId = task.getStatus().getId().toString();
    }

    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return title;
    }
    public void setTaskName(String taskName) {
        this.title = taskName;
    }
    public String getTaskDescription() {
        return description;
    }
    public void setTaskDescription(String taskDescription) {
        this.description = taskDescription;
    }
    public List<String> getCategoriesId() {
        return categoriesId;
    }
    public void setCategoriesId(List<String> categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getStatusId() {
        return statusId;
    }
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }


}
