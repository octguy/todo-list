package com.example.todobe.repository;

import com.example.todobe.model.Task;
import com.example.todobe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

    List<Task> findByUserAndDeletedAtIsNull(User user);

    Optional<Task> findByTaskIdAndUser(Integer taskId, User user);

    Optional<Task> findByTaskIdAndUserAndDeletedAtIsNull(Integer taskId, User user);
}
