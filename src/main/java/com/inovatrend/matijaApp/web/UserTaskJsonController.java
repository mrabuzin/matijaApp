package com.inovatrend.matijaApp.web;

import com.inovatrend.matijaApp.domain.Task;
import com.inovatrend.matijaApp.domain.User;
import com.inovatrend.matijaApp.service.TaskManager;
import com.inovatrend.matijaApp.service.UserManager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json")
public class UserTaskJsonController {

    private final UserManager userManager;
    private final TaskManager taskManager;

    public UserTaskJsonController(UserManager userManager, TaskManager taskManager) {
        this.userManager = userManager;
        this.taskManager = taskManager;
    }


    @GetMapping("/user")
    public List<User> getJsonUsers() {
        return userManager.getAllUsers();
    }

    @GetMapping("/task")
    public List<Task> getJsonTasks() {
        return taskManager.getAllTasks();
    }

}
