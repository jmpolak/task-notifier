package jmpolak.task_notification.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {
    String to;
    String subject;
    String htmlBody;
}
