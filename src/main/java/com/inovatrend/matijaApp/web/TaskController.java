package com.inovatrend.matijaApp.web;

import com.inovatrend.matijaApp.domain.Task;
import com.inovatrend.matijaApp.service.TaskManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("task")
@RequestMapping("/task")
public class TaskController {

    private final TaskManager taskManager;


    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @RequestMapping(value = "/list")
    public String listAllTasks(Model model) {

        List<Task> allTasks = taskManager.getAllTasks();
        model.addAttribute("tasks", allTasks);
        return "list-tasks";
    }

    @GetMapping("/create")
    public String showCreateTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
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

    @ResponseBody
    @RequestMapping(value = "/rest/create", method = RequestMethod.POST)
    public Task createTaskRest(@RequestBody Task task) {
        return task;
    }

}
