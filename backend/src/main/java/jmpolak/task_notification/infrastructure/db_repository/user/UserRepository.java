package jmpolak.task_notification.infrastructure.db_repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jmpolak.task_notification.core.entity.User;
import jmpolak.task_notification.core.port.IUserRepository;
import jmpolak.task_notification.infrastructure.db.mongo.model.UserModel;

@Repository
public class UserRepository implements IUserRepository {
    private final MongoTemplate mongoClient;

    public UserRepository(MongoTemplate mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public User save(User user) {
        return this.mongoClient.save(new UserModel(user.getEmail(), user.getPassword()));
    }

    @Override
    public boolean remove(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public User update(String id, User object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<User> getUserByMail(String mail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(mail));
        return Optional.ofNullable(this.mongoClient.findOne(query, UserModel.class));
    }

}
