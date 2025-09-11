package com.example.todobe.controller;

import com.example.todobe.dto.TaskDto;
import com.example.todobe.dto.request.CreateTaskRequest;
import com.example.todobe.dto.request.UpdateTaskRequest;
import com.example.todobe.model.ApiResponse;
import com.example.todobe.service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskDto>>> findAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        ApiResponse<List<TaskDto>> apiResponse = new ApiResponse<>(
                HttpStatus.OK,
                "Tasks retrieved successfully",
                tasks,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> getTaskById(@PathVariable Integer id) {
        TaskDto task = taskService.getTaskById(id);
        ApiResponse<TaskDto> apiResponse = new ApiResponse<>(
                HttpStatus.OK,
                "Task retrieved successfully",
                task,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskDto>> createTask(@RequestBody @Valid CreateTaskRequest request) {
        TaskDto task = taskService.createTask(request);
        ApiResponse<TaskDto> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED,
                "Task created successfully",
                task,
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> updateTask(@PathVariable Integer id, @RequestBody @Valid UpdateTaskRequest request) {
        TaskDto task = taskService.updateTask(id, request);
        ApiResponse<TaskDto> apiResponse = new ApiResponse<>(
                HttpStatus.OK,
                "Task updated successfully",
                task,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                HttpStatus.NO_CONTENT,
                "Task deleted successfully",
                null,
                null
        );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
    }
}
