package org.pinky83.dao;

import org.pinky83.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserDao extends JpaRepository<User, Integer> {
    List<User> findAll();

    User getById(Integer id);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    @Transactional
    void deleteById(Integer integer);

    User getByEmail(String email);

    User getByName(String name);
}
