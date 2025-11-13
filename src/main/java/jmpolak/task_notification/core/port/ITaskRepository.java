package jmpolak.task_notification.core.port;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import jmpolak.task_notification.core.entity.Task;
@Repository
public interface ITaskRepository {
    public List<Task> getAll();
    public List<Task> findTasksByDate(LocalDate date);
    public Task save(Task task);
    public boolean remove(String id);
    public void getById(String id);
    public Task update(String id, Task task);
}
