package com.timetracker.service;

import com.timetracker.db.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> findAllUser(int offset, int limit, String orderBy, String search) throws SQLException;

    List<User> findAllUser() throws SQLException;

    int getUserCount(String search) throws SQLException;

    void deleteUser(int userId) throws SQLException;
}
