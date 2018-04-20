package com.inovatrend.matijaApp.service;

import com.inovatrend.matijaApp.dao.TaskRepository;
import com.inovatrend.matijaApp.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskManagerClass implements TaskManager{


    private final TaskRepository taskRepository;

    public TaskManagerClass(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        taskRepository.deleteByUserId(userId);
    }

    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }
}
