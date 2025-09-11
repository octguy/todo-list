package com.example.todobe.service;

import com.example.todobe.dto.TaskDto;
import com.example.todobe.dto.request.CreateTaskRequest;
import com.example.todobe.dto.request.UpdateTaskRequest;

import java.util.List;

public interface ITaskService {

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(Integer id);

    TaskDto createTask(CreateTaskRequest request);

    TaskDto updateTask(Integer taskId, UpdateTaskRequest request);

    void deleteTask(Integer id);
}
