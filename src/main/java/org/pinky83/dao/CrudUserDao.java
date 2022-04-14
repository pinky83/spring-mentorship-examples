package org.pinky83.dao;

import org.pinky83.pojo.User;

public interface CrudUserDao extends GenericDao<User>{
    User getByEmail(String email);

    User getByName(String name);
}
