package com.example.Planner.Services;

import com.example.Planner.Models.Task;
import com.example.Planner.Models.User;
import com.example.Planner.Repository.TaskRepository;
import com.example.Planner.Repository.UserRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TasksService {
    
    private final TaskRepository taskRepository; 
    private final UserRepository userRepository;

    public List<Task> getAllTasks(Principal principal) {
        Stream<Task> stream = taskRepository
                .findAll()
                .stream()
                .filter(task -> task.getUser() == getUserByPrincipal(principal).getId());
        List<Task> list = new ArrayList<>();
        stream.forEach(item -> list.add(item));
        return list;
    }

    public void saveTask(Principal principal, Task task) {
        task.setUser(getUserByPrincipal(principal).getId());
        taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

}
