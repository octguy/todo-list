package com.example.todobe.service;

import com.example.todobe.dto.TaskDto;

import java.util.List;

public interface ITaskService {

    List<TaskDto> getAllTasks();
}
