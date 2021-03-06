package com.inovatrend.matijaApp.web;

import com.inovatrend.matijaApp.domain.Sex;
import com.inovatrend.matijaApp.service.TaskManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.inovatrend.matijaApp.domain.User;
import com.inovatrend.matijaApp.service.UserManager;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@SessionAttributes("user")
@RequestMapping("/user")
public class UserController {


    private final UserManager userManager;
    private final TaskManager taskManager;


    public UserController(UserManager userManager, TaskManager taskManager) {
        this.userManager = userManager;

        this.taskManager = taskManager;
    }

    @RequestMapping(value = "/list")
    public String listAllUsers(Model model) {

        List<User> allUsers = userManager.getAllUsers();

        model.addAttribute("users", allUsers);

        return "list-users";
    }

//    @GetMapping("/edit")
//    public String editUser( Model model, @PathVariable(required = false) Long userId) {
//        if(userId != null) {
//            Optional<User> user = userManager.getUser(userId);
//            model.addAttribute("user", user);
//
//
//        } else {
//            User user = new User(null, "", "", "", null);
//            model.addAttribute("user", user);
//        }
//        model.addAttribute("sexOptions", Sex.values());
//        return "create-user";
//    }
    @GetMapping("/create")
    public String createUser( Model model) {

        User user = new User(null, "", "", "", null);
        model.addAttribute("user", user);
        model.addAttribute("sexOptions", Sex.values());
        return "create-user";
    }


    @PostMapping("/create")
    public String processCreateUserForm(Model model, @ModelAttribute @Valid User user, BindingResult result){

        if(result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            model.addAttribute("user", user);
            return "create-user";
        }
        else {
            // save to DB
            User savedUser = userManager.save(user);
            model.addAttribute("user", savedUser);
            return "user-created";
        }
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        taskManager.deleteByUserId(userId);
        userManager.deleteUser(userId);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{userId}")
    public String editUser( Model model, @PathVariable Long userId) {
        Optional<User> user = userManager.getUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("sexOptions", Sex.values());
        return "create-user";

    }


    @ResponseBody
    @RequestMapping(value = "/rest/create", method = RequestMethod.POST)
    public User createUserRest(@RequestBody User user) {
        return user;
    }
}
