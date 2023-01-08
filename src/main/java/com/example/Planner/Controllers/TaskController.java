package com.example.Planner.Controllers;

import com.example.Planner.Models.Task;
import com.example.Planner.Models.User;
import com.example.Planner.Services.TasksService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TaskController {
    
    private final TasksService tasksService;
    
    @GetMapping("/tasks")
    public String tasks(Model model, Principal principal) {
        model.addAttribute("user", tasksService.getUserByPrincipal(principal));
        model.addAttribute("tasks", tasksService.getAllTasks(principal));
        return "tasks";
    }
    
    @PostMapping("/tasks")
    public String saveTask(@ModelAttribute Task task, Principal principal) {
        tasksService.saveTask(principal, task);
        return "redirect:/tasks";
    }
    
    @GetMapping("/tasks/new")
    public String addTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "new_task";
    }
    
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        tasksService.deleteTaskById(id);
        return "redirect:/tasks";
    }
}