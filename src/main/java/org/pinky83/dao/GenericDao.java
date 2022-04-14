package org.pinky83.dao;

import java.util.List;

public interface GenericDao<T> {
    T save(T entity);

    boolean delete(T entity);

    T getById(int id);

    List<T> findAll();
}
