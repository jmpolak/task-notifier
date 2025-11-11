package jmpolak.task_notification.core.entity;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class Task {
    String id; // optional - from db
    String title;
    String note;
    String to;
    boolean attachment;
    boolean completed;
    LocalDateTime notificationDate;
    LocalDateTime createDate;

    public Task(String title, String note, String to, boolean attachment, boolean completed, LocalDateTime notificationDate){
        this.title = title;
        this.note = note;
        this.to = to;
        this.attachment = attachment;
        this.completed = completed;
        this.notificationDate = notificationDate;
        this.createDate = LocalDateTime.now();
    }
}
