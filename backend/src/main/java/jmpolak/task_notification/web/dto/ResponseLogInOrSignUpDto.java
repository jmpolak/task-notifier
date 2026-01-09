package jmpolak.task_notification.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class ResponseLogInOrSignUpDto {
    private boolean success;
    private String token;

    public static ResponseLogInOrSignUpDto toResponse(String token) {
        return new ResponseLogInOrSignUpDto(true, token);
    }
}
