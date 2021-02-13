package com.timetracker.service.impl;

import com.timetracker.db.entity.User;
import com.timetracker.db.repository.dao.DBManager;
import com.timetracker.db.repository.dao.UserDAO;
import com.timetracker.service.RegistrationService;

import java.sql.SQLException;

public class RegistrationServiceImpl implements RegistrationService {

    private DBManager dbManager = DBManager.getInstance();
    private UserDAO userDAO = new UserDAO();

    public boolean registrateNewUser(User user) throws SQLException {
        User currentUser = userDAO.getUser(user.getLogin(), dbManager.getConnection());

        if(currentUser != null){
            return false;
        }

//        user.getPassword()

        return userDAO.addUser(user, dbManager.getConnection()) != null;

    }
}
