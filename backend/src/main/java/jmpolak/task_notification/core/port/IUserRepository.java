package jmpolak.task_notification.core.port;

import java.util.Optional;

import jmpolak.task_notification.core.entity.User;

public interface IUserRepository extends IGenericRepository<User> {
    public Optional<User> getUserByMail(String mail);

}