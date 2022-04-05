package org.pinky83.service;

import java.util.List;

public interface GenericService<T> {
    List<T> getAll(Integer userId);

    T getById(Integer id, Integer userId);

    boolean delete(Integer id, Integer userId);

    public T create(T t, Integer userId);

    public void update(T newT, Integer userId);

}
