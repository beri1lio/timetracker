package com.timetracker.service.impl;

import com.timetracker.db.entity.User;
import com.timetracker.db.repository.dao.DBManager;
import com.timetracker.db.repository.dao.UserDAO;
import com.timetracker.service.AuthorizationService;

import java.sql.SQLException;

public class AuthorizationServiceImpl implements AuthorizationService {

    private DBManager dbManager = DBManager.getInstance();
    private UserDAO userDAO = new UserDAO();
    @Override
    public User authorizateUser(User user) throws SQLException {

        return userDAO.getUser(user.getLogin(), user.getPassword(), dbManager.getConnection());
    }
}
