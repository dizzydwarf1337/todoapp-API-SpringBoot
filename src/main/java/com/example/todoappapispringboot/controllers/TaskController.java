package com.example.todoappapispringboot.controllers;

import com.example.todoappapispringboot.dtos.ApiResponse;
import com.example.todoappapispringboot.dtos.Task.*;
import com.example.todoappapispringboot.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tasks")
public class TaskController extends BaseController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ApiResponse<TaskDto>> GetTaskById(@PathVariable String taskId){
        try{
            var task = taskService.GetTaskById(UUID.fromString(taskId));
            return handleResponse(ApiResponse.success(task));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<ApiResponse<List<TaskDto>>> GetTasksByUserId(@PathVariable String userId) {
        try{
            var tasks = taskService.GetTasksByUserId(UUID.fromString(userId));
            return handleResponse(ApiResponse.success(tasks));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> CreateTask(@RequestBody CreateTaskDto createTaskDto){
        try{
            taskService.CreateTask(createTaskDto);
            return handleResponse(ApiResponse.success("Task created"));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<ApiResponse<String>> DeleteTask(@PathVariable String taskId) {
        try{
            taskService.DeleteTask(UUID.fromString(taskId));
            return handleResponse(ApiResponse.success("Task deleted"));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<ApiResponse<String>> EditTask(@RequestBody EditTaskDto editTaskDto){
        try{
            taskService.EditTask(editTaskDto);
            return handleResponse(ApiResponse.success("Task updated"));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }
    @PatchMapping("/updateStatus")
    public ResponseEntity<ApiResponse<String>> UpdateStatus(@RequestBody EditTaskStatusDto editTaskStatusDto){
        try{
            taskService.UpdateTaskStatus(editTaskStatusDto);
            return handleResponse(ApiResponse.success("Task updated"));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }

    @PatchMapping("/updateCategories")
    public ResponseEntity<ApiResponse<String>> UpdateCategories(@RequestBody EditTaskCategoriesDto editTaskCategoriesDto){

        try{
            taskService.UpdateTaskCategories(editTaskCategoriesDto);
            return handleResponse(ApiResponse.success("Task updated"));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }

}
