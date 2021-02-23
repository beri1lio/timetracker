package com.timetracker.service;

import com.timetracker.db.entity.User;

import java.sql.SQLException;

/**
 * The interface contains methods to process requests from controller to DAO layer.
 */
public interface RegistrationService {

    boolean registrateUser(User user) throws SQLException;
}
