package com.enterprise.taskmanagement.controller;
 
import com.enterprise.taskmanagement.dto.TaskDTO;
import com.enterprise.taskmanagement.entity.Task;
import com.enterprise.taskmanagement.security.UserDetailsImpl;
import com.enterprise.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    
    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<TaskDTO>> getTasksByAssignedUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByAssignedUser(userId));
    }
    
    @GetMapping("/created/{userId}")
    public ResponseEntity<List<TaskDTO>> getTasksByCreator(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByCreator(userId));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskDTO>> getTasksByStatus(@PathVariable Task.Status status) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }
    
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<TaskDTO>> getTasksByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(taskService.getTasksByTeam(teamId));
    }
    
    @GetMapping("/upcoming/{days}")
    public ResponseEntity<List<TaskDTO>> getUpcomingTasks(@PathVariable int days) {
        return ResponseEntity.ok(taskService.getUpcomingTasks(days));
    }
    
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody Task task, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ResponseEntity<>(taskService.createTask(task, userDetails.getId()), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
