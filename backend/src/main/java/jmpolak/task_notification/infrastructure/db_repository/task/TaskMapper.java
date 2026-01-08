package jmpolak.task_notification.infrastructure.db_repository.task;

import jmpolak.task_notification.core.entity.Task;
import jmpolak.task_notification.infrastructure.db.mongo.model.TaskModel;

public class TaskMapper {
    public static TaskModel toModel(Task doc) {
        return new TaskModel(doc.getTitle(), doc.getNote(), doc.getTo(), doc.isAttachment(), doc.isCompleted(),
                doc.getNotificationDate(), doc.getCreateDate());
    }

    public static Task toEntity(TaskModel entity) {
        return new Task(entity.getId(), entity.getTitle(), entity.getNote(), entity.getTo(), entity.isAttachment(),
                entity.isCompleted(), entity.getNotificationDate(), entity.getCreateDate());
    }
}
