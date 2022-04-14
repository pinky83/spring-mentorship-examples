package org.pinky83.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.pinky83.configuration.AppConfiguration;
import org.pinky83.dao.CrudUserDao;
import org.pinky83.pojo.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.pinky83.dao.Constants.*;

@Repository
@Qualifier("jdbc")
@Slf4j
public class JdbcUserDao implements CrudUserDao {

    private final AppConfiguration configuration;

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
        try (Connection connection = configuration.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setBoolean(4, entity.isEnabled());
            statement.setDate(5, Date.valueOf(entity.getRegistered()));

            statement.execute();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                entity.setId(result.getInt(1));
            }
        } catch (SQLException e) {
            log.info("Error while saving user data", e);
            return null;
        }

        return entity;
    }

    @Override
    public boolean delete(User entity) {
        User result = getById(entity.getId());
        if (Objects.nonNull(result)) {
            try(Connection connection = configuration.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);

                statement.setInt(1, entity.getId());
                statement.execute();
                return true;
            } catch (SQLException e) {
                log.info("error while deleting user data", e);
            }
        }
        return false;
    }

    @Override
    public User getById(int id) {
        User result = null;

        try(Connection connection = configuration.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = new User();
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setEmail(resultSet.getString("email"));
                result.setPassword(resultSet.getString("password"));
                result.setRegistered(resultSet.getDate("registered").toLocalDate());
                result.setEnabled(resultSet.getBoolean("enabled"));
            }
        } catch (SQLException e) {
            log.info("error while getting user data", e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();

        try(Connection connection = configuration.getConnection()) {
           PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
           ResultSet resultSet = statement.executeQuery();

           while (resultSet.next()) {
               User user = new User();
               user.setId(resultSet.getInt("id"));
               user.setName(resultSet.getString("name"));
               user.setEmail(resultSet.getString("email"));
               user.setPassword(resultSet.getString("password"));
               user.setRegistered(resultSet.getDate("registered").toLocalDate());
               user.setEnabled(resultSet.getBoolean("enabled"));

               result.add(user);
           }
        } catch (SQLException e) {
            log.info("error while getting user data", e);
        }

        return result;
    }
}
