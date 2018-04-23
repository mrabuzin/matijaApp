package com.inovatrend.matijaApp.web;

import com.inovatrend.matijaApp.domain.Task;
import com.inovatrend.matijaApp.domain.User;
import com.inovatrend.matijaApp.service.TaskManager;
import com.inovatrend.matijaApp.service.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("task")
@RequestMapping("/task")
public class TaskController {

    private final TaskManager taskManager;
    private final UserManager userManager;



    public TaskController(TaskManager taskManager, UserManager userManager) {
        this.taskManager = taskManager;
        this.userManager = userManager;
    }

    @RequestMapping(value = "/list")
    public String listAllTasks(Model model, @RequestParam(required = false) Long userId) {

        if (userId == null){
            List<Task> allTasks = taskManager.getAllTasks();
            model.addAttribute("tasks", allTasks);
        }
        else {
            List<Task> userTasks = taskManager.findByUserId(userId);
            model.addAttribute("tasks", userTasks);
            model.addAttribute("selectedUser" , userId);
        }


        List<User> allUsers = userManager.getAllUsers();

        model.addAttribute("users" , allUsers);

        return "list-tasks";
    }

    @GetMapping("/create")
    public String showCreateTaskForm(Model model) {
        Task task = new Task();
        List<User> allUsers = userManager.getAllUsers();
        model.addAttribute("task", task);
        model.addAttribute("users" , allUsers);
        return "create-task";
    }

    @PostMapping("/create")
    public String processCreateTaskForm(Model model, @ModelAttribute @Valid Task task, BindingResult result){

        if(result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            model.addAttribute("task", task);
            return "create-task";
        }
        else {
            // save to DB
            Task savedTask = taskManager.save(task);
            model.addAttribute("task", savedTask);
            return "task-created";
        }
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskManager.deleteTask(taskId);
        return "redirect:/task/list";
    }

    @GetMapping("/edit/{taskId}")
    public String editTask( Model model, @PathVariable Long taskId) {
        Optional<Task> task = taskManager.getTask(taskId);
        model.addAttribute("task", task);

        List<User> allUsers = userManager.getAllUsers();
        model.addAttribute("users" , allUsers);
        return "create-task";
    }

    @ResponseBody
    @RequestMapping(value = "/rest/create", method = RequestMethod.POST)
    public Task createTaskRest(@RequestBody Task task) {
        return task;
    }

}
