package com.timetracker.service.impl;

import com.timetracker.db.entity.Status;
import com.timetracker.db.entity.TaskTableItem;
import com.timetracker.db.entity.Task;
import com.timetracker.db.repository.dao.ConnectionPool;
import com.timetracker.db.repository.dao.TaskDAO;
import com.timetracker.service.TaskService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

/**
 * Provides implementation of all {@code TaskServiceImpl} interface methods.
 */
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

    public List<TaskTableItem> getUnapprovedTasks() throws SQLException {
        connection = connectionPool.getConnection();
        List<TaskTableItem> tasks = taskDAO.getUnapprovedTasks(connection);
        connection.close();
        return tasks;
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
    public List<Task> findAllTasks() throws SQLException{
        connection = connectionPool.getConnection();
        List<Task> allTasks = taskDAO.findAllTasks(connection);
        connection.close();
        return allTasks;
    }

    @Override
    public int getTaskCount(String search) throws SQLException {
        connection = connectionPool.getConnection();
        int taskCount = taskDAO.getTaskCount(search, connection);
        connection.close();
        return taskCount;
    }

    @Override
    public int getTaskCount(int userId) throws SQLException {
        connection = connectionPool.getConnection();
        int taskCount = taskDAO.getTaskCount(userId, connection);
        connection.close();
        return taskCount;
    }

    @Override
    public List<Task> findTasksByUserId(int id, int offset, int limit) throws SQLException {
        connection = connectionPool.getConnection();
        List<Task> allTasks = taskDAO.getTasksByUserId(id, offset, limit, connection);
        connection.close();
        return allTasks;
    }

    @Override
    public void updateStatus(int taskId, Status status) throws SQLException {
        connection = connectionPool.getConnection();
        taskDAO.updateStatus(taskId, status, connection);
        connection.close();
    }
}

