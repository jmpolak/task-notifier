package jmpolak.task_notification.web.dto;

import java.time.LocalDateTime;

import jmpolak.task_notification.core.entity.Task;

public class ResponseTaskDto extends Task {

    public ResponseTaskDto(String id, String title, String note, String to, boolean attachment, boolean completed, LocalDateTime notificationDate,
            LocalDateTime createDate) {
        super(id, title, note, to, attachment, completed, notificationDate, createDate);
    }
    
    public static ResponseTaskDto toResponse(Task task){
        return new ResponseTaskDto(task.getId(), task.getTitle(), 
            task.getNote(), task.getTo(), false, task.isCompleted(), task.getNotificationDate(), task.getCreateDate());
    }
}
