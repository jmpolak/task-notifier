package jmpolak.task_notification.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jmpolak.task_notification.application.service.MailService;

@RestController()
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("execute")
    public void execute() {
        this.mailService.sendMail();
    }
}
