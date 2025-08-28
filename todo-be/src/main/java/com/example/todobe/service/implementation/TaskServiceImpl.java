package com.example.todobe.service.implementation;

import com.example.todobe.dto.TaskDto;
import com.example.todobe.model.Task;
import com.example.todobe.repository.TaskRepository;
import com.example.todobe.service.ITaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public TaskDto mapToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(task.getTaskId());
        taskDto.setUserId(task.getUser().getUserId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setIsCompleted(task.getIsCompleted());
        taskDto.setDeletedAt(task.getDeletedAt());
        return taskDto;
    }
}
