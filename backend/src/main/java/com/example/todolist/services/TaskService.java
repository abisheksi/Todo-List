package com.example.todolist.services;

import com.example.todolist.exceptions.TaskNotFoundByIdException;
import com.example.todolist.models.Task;
import com.example.todolist.models.TaskDTO;
import com.example.todolist.repositories.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> viewAllTasks() {
        return taskRepository.findAll(Sort.by("taskId").ascending());
    }

    public List<Task> viewAllCompletedTask() {
        return taskRepository.findAll(Sort.by("taskId").ascending())
                .stream()
                .filter(Task::isCompletion)
                .toList();
    }

    public List<Task> viewAllPendingTask() {
        return taskRepository.findAll(Sort.by("taskId").ascending())
                .stream()
                .filter(task -> !task.isCompletion())
                .toList();
    }

    public Task findTaskById(int id) {

        Task task = taskRepository.findByTaskId(id);
        if (task == null) throw new TaskNotFoundByIdException();

        return task;
    }

    public void addATaskToQueue(TaskDTO newTask) {
        Task task = new Task();
        task.setTaskName(newTask.getTaskName());
        task.setTaskDescription(newTask.getTaskDescription());

        taskRepository.save(task);
    }

    public void setTaskCompleted(int id) {
        Task task = taskRepository.findByTaskId(id);

        if (task == null) throw new TaskNotFoundByIdException();

        task.setCompletion(true);

    }

    public void changeTask(int id, TaskDTO updatedTask) {
        Task task = taskRepository.findByTaskId(id);

        if (task == null) throw new TaskNotFoundByIdException();

        task.setTaskName(updatedTask.getTaskName());
        task.setTaskDescription(updatedTask.getTaskDescription());


    }

    public void changeTaskDescription(int id, TaskDTO updatedTask) {
        Task task = taskRepository.findByTaskId(id);

        if (task == null) throw new TaskNotFoundByIdException();

        task.setTaskDescription(updatedTask.getTaskDescription());
    }


}
