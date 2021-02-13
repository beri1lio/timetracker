package com.timetracker.service;

import com.timetracker.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<UserDto> findAllTasks(int offset, int limit, String orderBy, String search) throws SQLException;

    void deleteUser(int userId) throws SQLException;
}
