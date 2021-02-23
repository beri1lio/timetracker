package com.timetracker.service.impl;

import com.timetracker.db.entity.User;
import com.timetracker.db.repository.dao.ConnectionPool;
import com.timetracker.db.repository.dao.UserDAO;
import com.timetracker.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Provides implementation of all {@code UserServiceImpl} interface methods.
 */
public class UserServiceImpl implements UserService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private UserDAO userDAO = new UserDAO();
    Connection connection = null;

    @Override
    public List<User> findAllUsers(int offset, int limit, String orderBy, String search) throws SQLException {
        connection = connectionPool.getConnection();
        List<User> allUsers = userDAO.findAllUsers(offset, limit, orderBy, search, connection);
        connection.close();
        return allUsers;
    }

    @Override
    public List<User> findAllUsers() throws SQLException {
        connection = connectionPool.getConnection();
        List<User> allUsers = userDAO.findAllUsers(connection);
        connection.close();
        return allUsers;
    }

    @Override
    public int getUserCount(String search) throws SQLException {
        connection = connectionPool.getConnection();
        int userCount = userDAO.getUserCount(search, connection);
        connection.close();
        return userCount;
    }

    @Override
    public void deleteUser(int userId) throws SQLException{
        connection = connectionPool.getConnection();
        userDAO.deleteUser(userId, connection);
        connection.close();
    }
}
