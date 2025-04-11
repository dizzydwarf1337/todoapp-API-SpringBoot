package com.example.todoappapispringboot.services;


import com.example.todoappapispringboot.dtos.Task.*;
import com.example.todoappapispringboot.models.Category;
import com.example.todoappapispringboot.models.Status;
import com.example.todoappapispringboot.models.Task;
import com.example.todoappapispringboot.repositories.CategoryRepository;
import com.example.todoappapispringboot.repositories.StatusRepository;
import com.example.todoappapispringboot.repositories.TaskRepository;
import com.example.todoappapispringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, StatusRepository statusRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.categoryRepository = categoryRepository;
    }

    public TaskDto GetTaskById(UUID taskId){
        var task = taskRepository.findById(taskId).orElseThrow(()->new RuntimeException("Task not found"));
        return new TaskDto(task);
    }

    public List<TaskDto> GetTasksByUserId(UUID userId){
        var user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        var tasks =  taskRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("Tasks not found"));
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            taskDtos.add(new TaskDto(task));
        }
        return taskDtos;
    }

    public Task CreateTask(CreateTaskDto createTaskDto){
        var user = userRepository.findById(UUID.fromString(createTaskDto.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        var status = statusRepository.findById(UUID.fromString(createTaskDto.getStatusId()))
                .orElseThrow(() -> new RuntimeException("Status not found"));

        var categories = createTaskDto.getCategoriesId().stream()
                .map(id -> categoryRepository.findById(UUID.fromString(id))
                        .orElseThrow(() -> new RuntimeException("Category not found: " + id)))
                .toList();
        var task = new Task();
        task.setTitle(createTaskDto.getTitle());
        task.setCategories(categories);
        task.setUser(user);
        task.setStatus(status);
        task.setDescription(createTaskDto.getDescription());
        taskRepository.save(task);
        return task;
    }

    public void DeleteTask(UUID taskId){
        taskRepository.deleteById(taskId);
    }

    public void EditTask(EditTaskDto editTaskDto){
        var task = taskRepository.findById(UUID.fromString(editTaskDto.getTaskId())).orElseThrow(() -> new RuntimeException("Task not found"));
        Status status = statusRepository.findById(UUID.fromString(editTaskDto.getStatusId())).orElseThrow(()-> new RuntimeException("Status not found"));
        List<Category> categories = new ArrayList<>();
        categories = editTaskDto.getCategoriesId().stream()
                .map(id -> categoryRepository.findById(UUID.fromString(id))
                        .orElseThrow(() -> new RuntimeException("Category not found: " + id)))
                .toList();
        task.setTitle(editTaskDto.getTitle());
        task.setDescription(editTaskDto.getDescription());
        task.getCategories().clear();

        for (String categoryId : editTaskDto.getCategoriesId()) {
            Category category = categoryRepository.findById(UUID.fromString(categoryId))
                    .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
            task.getCategories().add(category);
        }
        task.setStatus(status);
        try {
            taskRepository.save(task);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Task could not be saved");
        }
    }

    public void UpdateTaskStatus(EditTaskStatusDto editTaskStatusDto){
        var task = taskRepository.findById(UUID.fromString(editTaskStatusDto.getTaskId())).orElseThrow(()->new RuntimeException("Task not found"));
        var status = statusRepository.findById(UUID.fromString(editTaskStatusDto.getStatusId())).orElseThrow(()->new RuntimeException("Status not found"));
        task.setStatus(status);
        taskRepository.save(task);
    }

    public void UpdateTaskCategories(EditTaskCategoriesDto editTaskCategoriesDto){
        var task = taskRepository.findById(UUID.fromString(editTaskCategoriesDto.getTaskId())).orElseThrow(() -> new RuntimeException("Task not found"));

        task.getCategories().clear();

        for (String categoryId : editTaskCategoriesDto.getCategoriesId()) {
            Category category = categoryRepository.findById(UUID.fromString(categoryId))
                    .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
            task.getCategories().add(category);
        }
        taskRepository.save(task);
    }



}
