package jmpolak.task_notification.web.dto;

import jmpolak.task_notification.core.entity.User;
import lombok.Getter;

public class CreateUserDto {
    public String email;
    public String password;

    public static User toEntity(CreateUserDto user) {
        return new User(user.email, user.password);
    }
}
