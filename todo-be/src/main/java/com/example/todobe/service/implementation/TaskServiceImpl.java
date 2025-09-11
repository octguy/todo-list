package com.example.todobe.service.implementation;

import com.example.todobe.dto.TaskDto;
import com.example.todobe.dto.request.CreateTaskRequest;
import com.example.todobe.exception.ResourceNotFoundException;
import com.example.todobe.model.Task;
import com.example.todobe.model.User;
import com.example.todobe.repository.TaskRepository;
import com.example.todobe.service.IAuthService;
import com.example.todobe.service.ITaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;
    private final IAuthService authService;

    public TaskServiceImpl(TaskRepository taskRepository, IAuthService authService) {
        this.taskRepository = taskRepository;
        this.authService = authService;
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

    @Override
    public List<TaskDto> getAllTasks() {
        User currentUser = authService.getCurrentUser();

        return taskRepository.findByUser(currentUser)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public TaskDto getTaskById(Integer id) {
        User currentUser = authService.getCurrentUser();

        Task task = taskRepository.findByTaskIdAndUser(id, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        return mapToDto(task);
    }

    @Override
    public TaskDto createTask(CreateTaskRequest request) {
        User currentUser = authService.getCurrentUser();

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDeadline(request.getDeadline());
        task.setIsCompleted(false);
        task.setUser(currentUser);

        Task savedTask = taskRepository.save(task);
        return mapToDto(savedTask);
    }
}
