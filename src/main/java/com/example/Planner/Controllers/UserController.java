package com.example.Planner.Controllers;

import com.example.Planner.Models.Task;
import com.example.Planner.Models.User;
import com.example.Planner.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }
    
    @PostMapping("/registration")
    public String createUser(Model model, @ModelAttribute User user) {
        if (!userService.createUser(user)) {
            model.addAttribute("error", "true");
            return "registration";
        }
        return "redirect:/login";
    }
}
