package jmpolak.task_notification.infrastructure.db_repository.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import jmpolak.task_notification.core.entity.Task;
import jmpolak.task_notification.core.port.ITaskRepository;
import jmpolak.task_notification.infrastructure.db.mongo.model.TaskModel;

@Repository
public class TaskRepository implements ITaskRepository{
    private final MongoTemplate mongoClient;
    public TaskRepository(MongoTemplate mongoClient){
        this.mongoClient = mongoClient;
    }

    @Override
    public List<Task> getAll() {
        List<TaskModel> list = this.mongoClient.findAll(TaskModel.class);
        return list.stream().map(lm -> TaskMapper.toDomain(lm)).toList();
    }

    @Override
    public Task save(Task task) {
        return TaskMapper.toDomain(this.mongoClient.save(TaskMapper.toEntity(task)));
    }

    @Override
    public void getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Task> findTasksByDate(LocalDate date) {
        // Convert LocalDate to start/end of day
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        // Build Mongo query
        Query query = new Query();
        query.addCriteria(Criteria.where("notificationDate").gte(startOfDay).lte(endOfDay));

        List<Task> tasks = mongoClient.find(query, TaskModel.class).stream().map(lm -> TaskMapper.toDomain(lm)).toList();
        return tasks;
    }
    
}
