package jmpolak.task_notification.application.cron;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jmpolak.task_notification.application.service.MailService;
import jmpolak.task_notification.application.service.TaskService;
import jmpolak.task_notification.core.entity.Task;

@Component
public class MailSenderScheduler {

    private final MailService mailService;
    private final TaskService taskService;

    public MailSenderScheduler(MailService mailService, TaskService taskService) {
        this.mailService = mailService;
        this.taskService = taskService;
    }

    // Runs every minute
    // @Scheduled(cron = "0 * * * * ?") // will it be 13:20:000 or seconds will be
    // not 0??????????????????????????????
    // @ToDo. Think about this because evey min we will make db lookups (maybe redis
    // for this day and update if new)
    // is there a way to set hours for this? we could get them
    public void sendDailyTaskNotifications() {
        List<Task> task = taskService.getTasksByDate(LocalDate.now());
        // filter tasks for time if its 13:20 we should send all tasks from 13:19:01 to
        // 13:20:00
        task.stream().forEach(t -> {
            mailService.createMailForTask(t);
            mailService.sendMail();
        });
    }
}
