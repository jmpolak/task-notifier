package jmpolak.task_notification.application.event;

import org.springframework.context.ApplicationEvent;

import jmpolak.task_notification.core.entity.Task;

public class ScheduleTaskForTodayEvent extends ApplicationEvent {
    private final Task task;

    public ScheduleTaskForTodayEvent(Object source, Task task) {
        super(source);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}