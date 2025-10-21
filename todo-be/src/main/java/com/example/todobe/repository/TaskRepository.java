package com.example.todobe.repository;

import com.example.todobe.model.Task;
import com.example.todobe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.user = :user and t.deletedAt IS NULL")
    List<Task> findByUser(User user);

    Optional<Task> findByTaskIdAndUser(Integer taskId, User user);
}
