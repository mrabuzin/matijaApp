package com.inovatrend.matijaApp.web;

import com.inovatrend.matijaApp.domain.Sex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.inovatrend.matijaApp.domain.User;
import com.inovatrend.matijaApp.service.UserManager;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@Controller
@SessionAttributes("user")
@RequestMapping("/user")
public class UserController {


    private final UserManager userManager;


    public UserController(UserManager userManager) {
        this.userManager = userManager;

    }

    @RequestMapping(value = "/list")
    public String listAllUsers(Model model) {

        List<User> allUsers = userManager.getAllUsers();

        model.addAttribute("users", allUsers);

        return "list-users";
    }


    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        User user = new User(null, "", "", "",null);
        model.addAttribute("user", user);

      // List<Sex> sexOptions = Arrays.asList(userManager.getAllSexes());
       // model.addAttribute("sexOptions" , sexOptions);

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
        userManager.deleteUser(userId);
        return "redirect:/user/list";
    }


    @ResponseBody
    @RequestMapping(value = "/rest/create", method = RequestMethod.POST)
    public User createUserRest(@RequestBody User user) {
        return user;
    }
}
