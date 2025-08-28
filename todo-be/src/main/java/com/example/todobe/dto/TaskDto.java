package com.example.todobe.dto;

import java.time.LocalDateTime;

public class TaskDto {

    private Integer taskId;
    private Integer userId;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Boolean isCompleted;
    private LocalDateTime deletedAt;

    public TaskDto() {
    }

    public TaskDto(Integer taskId, Integer userId, String title, String description, LocalDateTime deadline, Boolean isCompleted, LocalDateTime deletedAt) {
        this.taskId = taskId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.deletedAt = deletedAt;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
