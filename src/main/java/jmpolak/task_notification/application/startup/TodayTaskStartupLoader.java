package jmpolak.task_notification.application.startup;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import jmpolak.task_notification.application.event.ScheduleTaskForTodayEvent;
import jmpolak.task_notification.core.entity.Task;
import jmpolak.task_notification.core.port.ITaskRepository;

@Component
public class TodayTaskStartupLoader {

    private final ITaskRepository iTaskRepository;;
    private final ApplicationEventPublisher publisher;

    public TodayTaskStartupLoader(ITaskRepository repo, ApplicationEventPublisher publisher) {
        this.iTaskRepository = repo;
        this.publisher = publisher;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        List<Task> todaysTasks = iTaskRepository.findTasksByDate(LocalDate.now());

        for (Task t : todaysTasks) {
            System.out.println("add task: " + t.getTitle());
            publisher.publishEvent(new ScheduleTaskForTodayEvent(this, t));
        }
    }
}
