package com.timetracker.service.impl;

import com.timetracker.dto.TaskDto;
import com.timetracker.dto.UserDto;
import com.timetracker.db.entity.Task;
import com.timetracker.db.entity.User;
import com.timetracker.db.repository.dao.DBManager;
import com.timetracker.db.repository.dao.TaskDAO;
import com.timetracker.db.repository.dao.UserDAO;
import com.timetracker.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private DBManager dbManager = DBManager.getInstance();
    private UserDAO userDAO = new UserDAO();
    private TaskDAO taskDAO = new TaskDAO();

    @Override
    public List<UserDto> findAllTasks(int offset, int limit, String orderBy, String search) throws SQLException {
        List<UserDto> userDtoList = new ArrayList<>();
        //ToDO fix
        List<User> list = userDAO.findAllUsers(offset, limit, orderBy, search, dbManager.getConnection());

        for (User currentUser : list) {
            List<Task> tasks = taskDAO.getTasksUserId(currentUser.getId(), dbManager.getConnection());
            List<TaskDto> taskDtos = new ArrayList<>();
            for (Task task : tasks) {
                TaskDto taskDto = new TaskDto(task.getId(), task.getName(), task.getTime());
                taskDtos.add(taskDto);
            }
            UserDto userDto = new UserDto(currentUser.getId(), currentUser.getName(), taskDtos);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public void deleteUser(int userId) throws SQLException{
        userDAO.deleteUser(userId, dbManager.getConnection());
    }
}
