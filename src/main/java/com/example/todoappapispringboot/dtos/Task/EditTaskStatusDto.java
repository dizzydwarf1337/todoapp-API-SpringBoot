package com.example.todoappapispringboot.dtos.Task;

public class EditTaskStatusDto {

    private String taskId;
    private String statusId;

    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getStatusId() {
        return statusId;
    }
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

}
