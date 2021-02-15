package com.timetracker.service.impl;

import com.timetracker.db.entity.User;
import com.timetracker.db.repository.dao.ConnectionPool;
import com.timetracker.db.repository.dao.UserDAO;
import com.timetracker.service.AuthorizationService;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthorizationServiceImpl implements AuthorizationService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private UserDAO userDAO = new UserDAO();
    @Override
    public User authorizateUser(User user) throws SQLException {
        Connection connection = connectionPool.getConnection();
        User currentUser = userDAO.getUser(user.getLogin(), user.getPassword(), connection);
        connection.close();
        return currentUser;
    }
}
