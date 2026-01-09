package jmpolak.task_notification.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmpolak.task_notification.application.service.UserService;
import jmpolak.task_notification.web.dto.LogInOrSignUp;
import jmpolak.task_notification.web.dto.ResponseLogInOrSignUpDto;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseLogInOrSignUpDto login(@Valid @RequestBody LogInOrSignUp entity) {
        // give back auth cookie
        return ResponseLogInOrSignUpDto.toResponse(this.userService.loginUser(LogInOrSignUp.toEntity(entity)));
    }

    @PostMapping("sign-up")
    public ResponseLogInOrSignUpDto signUp(@Valid @RequestBody LogInOrSignUp entity) {
        return ResponseLogInOrSignUpDto.toResponse(this.userService.createUserAndLogin(LogInOrSignUp.toEntity(entity)));
    }
}
