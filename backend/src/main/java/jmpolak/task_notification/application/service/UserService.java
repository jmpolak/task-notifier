package jmpolak.task_notification.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import jmpolak.task_notification.application.utils.JwtUtil;
import jmpolak.task_notification.application.utils.PasswordUtil;
import jmpolak.task_notification.core.entity.User;
import jmpolak.task_notification.core.port.IUserRepository;

@Service
public class UserService {

    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public String createUserAndLogin(User user) {
        // Encrypt pwd
        this.iUserRepository.getUserByMail(user.getEmail())
                .ifPresent(userFound -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
                });
        this.iUserRepository.save(new User(user.getEmail(), PasswordUtil.hashPassword(user.getPassword())));
        return JwtUtil.generateToken(user.getEmail());
    }

    public String loginUser(User user) {
        // check if user exists
        User userFound = this.iUserRepository.getUserByMail(user.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")); // @ToDo make
                                                                                                           // custom
                                                                                                           // Exception
        if (!PasswordUtil.matchPassword(user.getPassword(), userFound.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password dont match"); // @ToDo make custom
                                                                                              // Exception
        }
        // if yes token
        // if no NoUserFoundException
        return JwtUtil.generateToken(user.getEmail());
    }

}
