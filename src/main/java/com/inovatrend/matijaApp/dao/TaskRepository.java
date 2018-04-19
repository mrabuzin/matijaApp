package com.inovatrend.matijaApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inovatrend.matijaApp.domain.Task;

import javax.transaction.Transactional;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);


    @Transactional
    void deleteByUserId(Long userId);

}
