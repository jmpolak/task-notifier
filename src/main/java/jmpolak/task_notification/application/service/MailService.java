package jmpolak.task_notification.application.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import jmpolak.task_notification.core.entity.Mail;
import jmpolak.task_notification.core.entity.Task;
import jmpolak.task_notification.core.port.IMailSenderPort;

@Service
public class MailService {

    private ResourceLoader resourceLoader;
    private String mailTemplate = "";
    private final IMailSenderPort iMailSenderPort;

    public MailService(IMailSenderPort iMailSenderPort, ResourceLoader resourceLoader) throws IOException {
        this.iMailSenderPort = iMailSenderPort;
        this.resourceLoader = resourceLoader;
        this.loadTemplate();
    }

    private void loadTemplate() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/mail/mail-template.html");
        this.mailTemplate = new String(resource.getInputStream().readAllBytes());
    }

    public void sendMail(Mail mail) {
        this.iMailSenderPort.sendMail(mail.getTo(), mail.getSubject(), mail.getHtmlBody());
    }

    public Mail createMailForTask(Task task) {

        Map<String, Object> map = new HashMap<>();
        map.put("title", task.getTitle());
        map.put("note", task.getNote());
        String text = new String(this.mailTemplate);
        for (var entry : map.entrySet()) {
            text = text.replace("{{" + entry.getKey() + "}}", entry.getValue().toString());
        }

        return new Mail(task.getTo(), task.getTitle(), text);
    }
}
