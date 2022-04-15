package org.pinky83.dao;

public class Constants {
    //TODO replace to messageSource strings
    public static final String INSERT_QUERY = "insert into users (name, email, password, enabled, registered)" +
            "values (?, ?, ?, ?, ?)";
    public static final String DELETE_QUERY = "delete from users where id=?";
    public static final String GET_BY_ID_QUERY = "select * from users where id=?";
    public static final String GET_BY_EMAIL_QUERY = "select * from users where email=?";
    public static final String GET_BY_NAME_QUERY = "select * from users where name = :name";
    public static final String GET_ALL_QUERY = "select * from users";
}
