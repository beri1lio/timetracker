package com.timetracker.service.impl;

import com.timetracker.db.entity.TaskTableItem;
import com.timetracker.dto.TaskDto;
import com.timetracker.db.entity.Task;
import com.timetracker.db.repository.dao.ConnectionPool;
import com.timetracker.db.repository.dao.TaskDAO;
import com.timetracker.service.TaskService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private TaskDAO taskDAO = new TaskDAO();
    private Connection connection = null;

    @Override
    public boolean newTask(Task task) throws SQLException {
        connection = connectionPool.getConnection();
        Task currentTask = taskDAO.getTask(task.getName(), connection);

        if (currentTask != null) {
            return false;
        }

        Task thisTask = taskDAO.addTask(connection, task);
        connection.close();
        return thisTask != null;
    }

    @Override
    public List<TaskDto> findTasksByUserId(int id) throws SQLException {
        connection = connectionPool.getConnection();
        List<Task> tasks = taskDAO.getTasksUserId(id, connection);
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto(task.getId(), task.getName(), task.getTime());
            taskDtos.add(taskDto);
        }
        connection.close();
        return taskDtos;
    }

    @Override
    public void updateTime(int taskId, Time newTime) throws SQLException {
        connection = connectionPool.getConnection();
        taskDAO.updateTime(taskId, newTime, connection);
        connection.close();
    }

    @Override
    public void deleteTask(int taskId) throws SQLException {
        connection = connectionPool.getConnection();
        taskDAO.deleteTask(taskId, connection);
        connection.close();
    }

    @Override
    public List<TaskTableItem> findAllTasks(int offset, int limit, String orderBy, String search) throws SQLException{
        connection = connectionPool.getConnection();
        List<TaskTableItem> allTasks = taskDAO.findAllTasks(offset, limit, orderBy, search, connection);
        connection.close();
        return allTasks;
    }

    @Override
    public int getTaskCount(String search) throws SQLException {
        connection = connectionPool.getConnection();
        int taskCount = taskDAO.getTaskCount(search, connectionPool.getConnection());
        connection.close();
        return taskCount;
    }

}

