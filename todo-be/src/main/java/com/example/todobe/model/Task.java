package com.example.todobe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="task")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}