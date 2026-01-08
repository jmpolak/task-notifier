package jmpolak.task_notification.core.port;

import java.util.List;

abstract interface IGenericRepository<T> {
    public List<T> getAll();

    public T save(T object);

    public boolean remove(String id);

    public T update(String id, T object);
}
