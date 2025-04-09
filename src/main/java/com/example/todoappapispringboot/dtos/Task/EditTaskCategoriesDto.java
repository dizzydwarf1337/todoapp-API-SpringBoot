package com.example.todoappapispringboot.dtos.Task;

import java.util.List;

public class EditTaskCategoriesDto {

    private String taskId;
    private List<String> categoriesId;

    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public List<String> getCategoriesId() {
        return categoriesId;
    }
    public void setCategoriesId(List<String> categoriesId) {
        this.categoriesId = categoriesId;
    }
}
