package com.inovatrend.matijaApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inovatrend.matijaApp.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
