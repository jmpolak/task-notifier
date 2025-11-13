package jmpolak.task_notification.application.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import jmpolak.task_notification.core.entity.Task;
import jmpolak.task_notification.core.port.ITaskRepository;

@Service
public class TaskService{
    public final ITaskRepository iTaskRepository;
    public TaskService(ITaskRepository iTaskRepository){
        this.iTaskRepository = iTaskRepository;
    }   

    public List<Task> getTasks(){
        return this.iTaskRepository.getAll();
    }

    public List<Task> getTasksByDate(LocalDate date){
        return this.iTaskRepository.findTasksByDate(date);
    }

    public Task saveTask(Task taskDto){
        return this.iTaskRepository.save(taskDto);
    }

    public boolean deleteTask(String id){
        return this.iTaskRepository.remove(id);
    }

    public Task updateTask(String id, Task task){
        return this.iTaskRepository.update(id, task);
    }

}