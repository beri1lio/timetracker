package com.timetracker.service;

import com.timetracker.db.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface contains methods to process requests from controller to DAO layer.
 */
public interface UserService {

    List<User> findAllUsers(int offset, int limit, String orderBy, String search) throws SQLException;

    List<User> findAllUsers() throws SQLException;

    int getUserCount(String search) throws SQLException;

    void deleteUser(int userId) throws SQLException;
}
