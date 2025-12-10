package jmpolak.task_notification.application.scheduler.task;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import jmpolak.task_notification.application.event.ScheduleTaskForTodayEvent;
import jmpolak.task_notification.application.service.MailService;
import jmpolak.task_notification.core.entity.Task;

@Service
public class TodayTaskScheduler implements ApplicationListener<ScheduleTaskForTodayEvent> {

    private final TaskScheduler scheduler;
    private final MailService mailService;

    public TodayTaskScheduler(TaskScheduler scheduler, MailService mailService) {
        this.scheduler = scheduler;
        this.mailService = mailService;
    }

    @Override
    public void onApplicationEvent(ScheduleTaskForTodayEvent event) {
        Task task = event.getTask();

        // Ensure task is for today
        if (!task.getNotificationDate().toLocalDate().equals(LocalDate.now())) {
            return; // ignore future tasks
        }

        // Ignore past tasks
        if (task.getNotificationDate().isBefore(LocalDateTime.now())) {
            return;
        }

        Instant instant = task.getNotificationDate().atZone(ZoneId.systemDefault()).toInstant();
        scheduler.schedule(() -> this.executeTask(task), instant);

        System.out.println("Scheduled todays task " + task.getId() + " at " + instant);
    }

    private void executeTask(Task task) {
        System.out.println("Executing todays task: " + task.getTitle());
        // Your notification logic here
        this.mailService.sendMail(this.mailService.createMailForTask(task));
    }
}
