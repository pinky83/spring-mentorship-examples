package org.pinky83.dao;

import org.pinky83.pojo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudUserDao extends CrudRepository<User, Integer> {
    List<User> getAll();

    User getById(Integer id);

    User getByEmail(String email);

    User getByName(String name);
}
