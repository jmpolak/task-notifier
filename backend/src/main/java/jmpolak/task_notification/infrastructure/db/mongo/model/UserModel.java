package jmpolak.task_notification.infrastructure.db.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jmpolak.task_notification.core.entity.User;
import lombok.Getter;

@Document(collection = "User")
@Getter
public class UserModel extends User {
    @Id
    private String id;

    public UserModel(String email, String password) {
        super(email, password);
    }

}
