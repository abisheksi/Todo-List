package com.example.todolist.controllers;

import com.example.todolist.models.Task;
import com.example.todolist.models.TaskDTO;
import com.example.todolist.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.viewAllTasks();
    }

    @GetMapping("/tasks/completed")
    public List<Task> getAllCompletedTasks() {
        return taskService.viewAllCompletedTask();
    }

    @GetMapping("/tasks/pending")
    public List<Task> getAllPendingTasks() {
        return taskService.viewAllPendingTask();
    }

    @GetMapping("/task")
    public Task getTaskById(@RequestParam int id) {
        return taskService.findTaskById(id);
    }

    @PostMapping("/task")
    public void postTask(@RequestBody TaskDTO newTask) {
        taskService.addATaskToQueue(newTask);
    }

    @PatchMapping("/task/{id}/completed")
    public void patchTaskCompleted(@PathVariable int id) {
        taskService.setTaskCompleted(id);
    }

    @PatchMapping("/update/task")
    public void patchTask(@RequestParam int id, @RequestBody TaskDTO updatedTask) {
        taskService.changeTask(id, updatedTask);
    }

    @PatchMapping("/update/description")
    public void patchTaskDescription(@RequestParam int id, @RequestBody TaskDTO updatedTask) {
        taskService.changeTaskDescription(id, updatedTask);
    }


}
