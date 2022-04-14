package org.pinky83.service;

import lombok.extern.slf4j.Slf4j;
import org.pinky83.dao.CrudUserDao;
import org.pinky83.pojo.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
@Slf4j
public class UserService implements GenericService<User> {
    private final CrudUserDao userDao;
    private final MessageSource messageSource;

    public UserService(@Qualifier("jdbcTemplate") CrudUserDao userDao, MessageSource messageSource) {
        this.userDao = userDao;
        this.messageSource = messageSource;
    }

    public List<User> getAll(Integer userId) {
        return userDao.findAll();
    }

    public User getById(Integer id, Integer userId) {
        return userDao.getById(id);
    }

    @Override
    public boolean delete(Integer id, Integer userId) {
        User entity = userDao.getById(id);
        if (Objects.nonNull(entity)) {
            userDao.delete(entity);
            return true;
        }
        return false;
    }

    @Override
    public User create(User entity, Integer userId) {
        return userDao.save(entity);
    }

    @Override
    public void update(User newT, Integer userId) {
        Assert.notNull(newT, messageSource.getMessage("user.notNull", null, Locale.getDefault()));
        User entity = userDao.getById(newT.getId());
        if (Objects.nonNull(entity)) {
            userDao.save(newT);
        }
    }

    User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    User getByName(String name) {
        return userDao.getByName(name);
    }
}
