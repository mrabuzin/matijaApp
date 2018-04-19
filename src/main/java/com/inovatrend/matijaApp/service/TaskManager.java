package com.inovatrend.matijaApp.service;

import com.inovatrend.matijaApp.domain.Task;

import java.util.List;

public interface TaskManager {

    Task save(Task task);
    List<Task> getAllTasks();
    void deleteTask(long id);
    List<Task> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
