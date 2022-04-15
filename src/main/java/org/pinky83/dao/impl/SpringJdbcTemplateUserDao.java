package org.pinky83.dao.impl;

import org.pinky83.dao.CrudUserDao;
import org.pinky83.pojo.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static org.pinky83.dao.Constants.*;

@Repository
@Qualifier("jdbcTemplate")
public class SpringJdbcTemplateUserDao implements CrudUserDao {

    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcTemplateUserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(GET_BY_EMAIL_QUERY, new BeanPropertyRowMapper<>(User.class), email);
    }

    @Override
    public User getByName(String name) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        User user = new User();
        user.setName(name);
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);

        return namedParameterJdbcTemplate.queryForObject(GET_BY_NAME_QUERY, namedParameters, new BeanPropertyRowMapper<>(User.class));
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
        return jdbcTemplate.update(DELETE_QUERY, entity.getId()) == 1;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(GET_ALL_QUERY, new BeanPropertyRowMapper<>(User.class));
    }
}
