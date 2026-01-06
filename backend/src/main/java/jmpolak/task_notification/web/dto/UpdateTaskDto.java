package jmpolak.task_notification.web.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jmpolak.task_notification.core.entity.Task;
import lombok.Getter;
@Getter
public class UpdateTaskDto {
    @NotBlank(message = "Title is required")
    @Size(max = 20, message = "Title must be under 20 characters")
    String title;
    @NotBlank(message = "note is required")
    @Size(max = 100, message = "Title must be under 100 characters")
    String note;
    boolean attachment;
    @NotBlank(message = "Recipient (to) is required")
    String to;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "Notification date is required")
    LocalDateTime notificationDate;

    public static Task toEntity(UpdateTaskDto taskDto){
        return new Task(taskDto.getTitle(), taskDto.getNote(), taskDto.getTo(), 
            false, false, taskDto.getNotificationDate());
    }
}
