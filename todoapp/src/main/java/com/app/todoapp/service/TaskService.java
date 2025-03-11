package com.app.todoapp.service;

import com.app.todoapp.models.Task;
import com.app.todoapp.repository.TaskRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){

        return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new IllegalIdentifierException("Invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
