package com.example.todobe.controller;

import com.example.todobe.dto.TaskDto;
import com.example.todobe.service.ITaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> findAllTasks() {
        return taskService.getAllTasks();
    }
}
