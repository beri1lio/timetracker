package com.timetracker.service;

import com.timetracker.db.entity.User;

import java.sql.SQLException;

public interface RegistrationService {

    boolean registrateNewUser(User user) throws SQLException;
}
