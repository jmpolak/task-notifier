package jmpolak.task_notification.core.port;

import java.time.LocalDate;
import java.util.List;
import jmpolak.task_notification.core.entity.Task;

public interface ITaskRepository extends IGenericRepository<Task> {
    public List<Task> findTasksByDate(LocalDate date);
}
