package jmpolak.task_notification.web.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jmpolak.task_notification.application.service.TaskService;
import jmpolak.task_notification.web.dto.CreateTaskDto;
import jmpolak.task_notification.web.dto.ResponseTaskDto;

@RestController
@RequestMapping("/task")
public class TaskController{

    private final TaskService taskService;
    
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("all")
    public List<ResponseTaskDto> getTask() {
        return taskService.getTasks().stream().map(t -> ResponseTaskDto.toResponse(t)).toList();
    }

    @GetMapping("by-date")
    public List<ResponseTaskDto> getTasksByDate(
        @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date
    ) {
        return taskService.getTasksByDate(date).stream().map(t -> ResponseTaskDto.toResponse(t)).toList();
    }

    @PostMapping("create")
    public ResponseTaskDto createTask(@Valid @RequestBody CreateTaskDto entity) {
        return ResponseTaskDto.toResponse(taskService.saveTask(CreateTaskDto.toEntity(entity)));
    }

    @PutMapping("update/{id}") // update task
    public String updateTask(@PathVariable String id, @RequestBody String entity) {
        return entity;
    }

    @DeleteMapping("delete/{id}") // update task
    public String deleteTask(@PathVariable String id) {
        return id;
    }
    
    
}