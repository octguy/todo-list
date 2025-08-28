package com.example.todobe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Integer taskId;
    private Integer userId;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Boolean isCompleted;
    private LocalDateTime deletedAt;
}
