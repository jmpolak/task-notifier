package jmpolak.task_notification.application.cron;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jmpolak.task_notification.application.event.ScheduleTaskForTodayEvent;
import jmpolak.task_notification.core.entity.Task;
import jmpolak.task_notification.core.port.ITaskRepository;

@Component
public class SetSchedulerCron {

    public final ITaskRepository iTaskRepository;;
    private final ApplicationEventPublisher publisher;

    public SetSchedulerCron(ITaskRepository repo, ApplicationEventPublisher publisher) {
        this.iTaskRepository = repo;
        this.publisher = publisher;
    }

    // Runs at midnight
    @Scheduled(cron = "0 0 0 * * *")
    public void sendDailyTaskNotifications() {
        System.out.println("Run Cron job to update schedule for: " + LocalDate.now().toString());
        List<Task> todaysTasks = iTaskRepository.findTasksByDate(LocalDate.now());

        for (Task t : todaysTasks) {
            System.out.println("add task: " + t.getTitle());
            publisher.publishEvent(new ScheduleTaskForTodayEvent(this, t));
        }

        // List<Task> task = taskService.getTasksByDate(LocalDate.now());
        // // filter tasks for time if its 13:20 we should send all tasks from 13:19:01
        // to 13:20:00
        // task.stream().forEach(t -> {
        // mailService.createMailForTask(t);
        // mailService.sendMail();
        // });
    }
}
