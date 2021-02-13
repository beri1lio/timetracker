package com.timetracker.service;

import com.timetracker.db.entity.User;

import java.sql.SQLException;

public interface AuthorizationService {

    User authorizateUser(User user) throws SQLException;
}
