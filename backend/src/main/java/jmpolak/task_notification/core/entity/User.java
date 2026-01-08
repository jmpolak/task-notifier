package jmpolak.task_notification.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class User {
    String id; // optional - from db
    @NonNull
    String email;
    @NonNull
    String password;
}
