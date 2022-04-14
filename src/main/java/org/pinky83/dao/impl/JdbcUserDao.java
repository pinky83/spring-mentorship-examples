package org.pinky83.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.pinky83.configuration.AppConfiguration;
import org.pinky83.dao.CrudUserDao;
import org.pinky83.pojo.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Slf4j
public class JdbcUserDao implements CrudUserDao {

    private AppConfiguration configuration;

    public JdbcUserDao(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getByName(String name) {
        return null;
    }

    @Override
    public User save(User entity) {
        try {
            Connection connection = configuration.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into users (name, email, password, enabled, registered)" +
                    "values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setBoolean(4, entity.isEnabled());
            statement.setDate(5, Date.valueOf(entity.getRegistered()));
        } catch (SQLException e) {
            log.info("Error while saving user data", e);
            return null;
        }

        return entity;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
