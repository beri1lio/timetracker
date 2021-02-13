package com.timetracker.service.impl;

import com.timetracker.db.entity.User;
import com.timetracker.db.repository.dao.DBManager;
import com.timetracker.db.repository.dao.UserDAO;
import com.timetracker.service.UserAdminService;

import java.sql.SQLException;
import java.util.List;

public class UserAdminServiceImpl implements UserAdminService {

    private DBManager dbManager = DBManager.getInstance();
    private UserDAO userDAO = new UserDAO();

    @Override
    public List<User> findAllUser(int offset, int limit, String orderBy, String search) throws SQLException {
        return userDAO.findAllUsers(offset, limit, orderBy, search, dbManager.getConnection());
    }

    @Override
    public List<User> findAllUser() throws SQLException {
        return userDAO.findAllUsers(dbManager.getConnection());
    }

    @Override
    public int getUserCount(String search) throws SQLException {
        return userDAO.getUserCount(search, dbManager.getConnection());
    }
}
