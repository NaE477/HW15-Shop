package services.interfaces;

import entities.base.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    T insert(T t);

    T findById(Integer id);

    List<T> findAll();

    T update(T t);

    void delete(T t);

    void truncate(String tableName);
}
