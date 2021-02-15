package com.timetracker.service.impl;

import com.timetracker.db.entity.User;
import com.timetracker.db.repository.dao.ConnectionPool;
import com.timetracker.db.repository.dao.UserDAO;
import com.timetracker.service.RegistrationService;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationServiceImpl implements RegistrationService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private UserDAO userDAO = new UserDAO();

    public boolean registrateNewUser(User user) throws SQLException {
        Connection connection = connectionPool.getConnection();
        User currentUser = userDAO.getUser(user.getLogin(), connection);

        if(currentUser != null){
            return false;
        }

//        toDo user.getPassword()

        User thisUser = userDAO.addUser(user, connection);
        connection.close();
        return thisUser != null;

    }
}
