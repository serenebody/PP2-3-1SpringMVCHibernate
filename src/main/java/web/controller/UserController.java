package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/table")
    public String showListUser(Model model) {
        List<User> userList = userService.getListUsers();
        model.addAttribute("users",userList);
        return "tableUsersAndEditUsers";
    }
    @GetMapping("/add")
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());
        return "addUserInTable";
    }
    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String lastName, @RequestParam String gender, Model model) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setGender(gender);
        userService.addUser(user);
        model.addAttribute("message","User added successfully");
        return "redirect:/table";
    }
    @PostMapping("/delete")
    public String deleteUser (@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/table";
    }
    @GetMapping("/update")
    public String updateUser(Model model, @RequestParam int id){
        model.addAttribute("user", userService.showUser(id));
        return "edit";
    }
    @PostMapping("/update")
    public String editUser (@ModelAttribute("user") User user) {
        User existingUser = userService.showUser(user.getId());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setLastName(user.getLastName());
            existingUser.setGender(user.getGender());
            userService.updateUser(existingUser );
        }
        return "redirect:/table";
    }
}
