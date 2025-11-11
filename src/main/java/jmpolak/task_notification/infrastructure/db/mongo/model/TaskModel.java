package jmpolak.task_notification.infrastructure.db.mongo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jmpolak.task_notification.core.entity.Task;
import lombok.Getter;

@Document(collection = "Task")
@Getter
public class TaskModel extends Task {
    @Id
    private String id;

    public TaskModel(String title, String note, String to, boolean attachment, boolean completed, LocalDateTime notificationDate, LocalDateTime createDate){
        super(title, note, to, attachment, completed, notificationDate);
    }

}
