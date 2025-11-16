package jmpolak.task_notification.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import jmpolak.task_notification.core.entity.Task;
import jmpolak.task_notification.core.port.IMailSenderPort;

@Service
public class MailService {

    private final IMailSenderPort iMailSenderPort;

    public MailService(IMailSenderPort iMailSenderPort) {
        this.iMailSenderPort = iMailSenderPort;
    }

    public void sendMail() {
        this.iMailSenderPort.sendMail("jeanmichel.polak@gmail.com", "test", "Test Mail");
    }

    public void createMailForTask(Task task) {

    }
}
