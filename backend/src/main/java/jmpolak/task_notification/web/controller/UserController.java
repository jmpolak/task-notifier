package jmpolak.task_notification.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jmpolak.task_notification.application.service.UserService;
import jmpolak.task_notification.web.dto.CreateUserDto;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // login
    @PostMapping("login")
    public String login(@Valid @RequestBody CreateUserDto entity) {
        // give back auth cookie
        return this.userService.loginUser(CreateUserDto.toEntity(entity));
    }

    @PostMapping("sign-up")
    public String signUp(@Valid @RequestBody CreateUserDto entity) {
        return this.userService.createUserAndLogin(CreateUserDto.toEntity(entity));
    }
    // sign up
}
