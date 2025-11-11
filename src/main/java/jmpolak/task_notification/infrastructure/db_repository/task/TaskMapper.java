package jmpolak.task_notification.infrastructure.db_repository.task;

import jmpolak.task_notification.core.entity.Task;
import jmpolak.task_notification.infrastructure.db.mongo.model.TaskModel;

public class TaskMapper {
    public static TaskModel toEntity(Task doc) {
        return new TaskModel(doc.getTitle(), doc.getNote(), doc.getTo(), doc.isAttachment(), doc.isCompleted(), doc.getNotificationDate(), doc.getCreateDate());
    }

    public static Task toDomain(TaskModel entity) {
        return new Task(entity.getId(), entity.getTitle(), entity.getNote(), entity.getTo(), entity.isAttachment(), entity.isCompleted(), entity.getNotificationDate(), entity.getCreateDate());
    }
}
