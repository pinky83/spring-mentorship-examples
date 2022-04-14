package org.pinky83.dao.impl;

import org.pinky83.dao.CrudUserDao;
import org.pinky83.pojo.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Qualifier("jdbcTemplate")
public class SpringJdbcTemplateUserDao implements CrudUserDao {

    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcTemplateUserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        Number key = jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(entity));
        entity.setId(key.intValue());

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
