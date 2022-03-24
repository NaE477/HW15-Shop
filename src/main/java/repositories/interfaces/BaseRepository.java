package repositories.interfaces;

import entities.base.BaseEntity;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
    T ins(T t);

    T readById(Integer id);

    List<T> readAll();

    T update(T t);

    void delete(T t);

    void truncate(String tableName);
}
