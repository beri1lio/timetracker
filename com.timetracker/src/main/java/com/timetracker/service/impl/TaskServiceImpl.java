package com.timetracker.service.impl;

import com.timetracker.db.entity.TaskTableItem;
import com.timetracker.dto.TaskDto;
import com.timetracker.db.entity.Task;
import com.timetracker.db.repository.dao.DBManager;
import com.timetracker.db.repository.dao.TaskDAO;
import com.timetracker.service.TaskService;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private DBManager dbManager = DBManager.getInstance();
    private TaskDAO taskDAO = new TaskDAO();

    @Override
    public boolean newTask(Task task) throws SQLException {
        Task currentTask = taskDAO.getTask(task.getName(), dbManager.getConnection());

        if (currentTask != null) {
            return false;
        }

        return taskDAO.addTask(dbManager.getConnection(), task) != null;
    }

    @Override
    public List<TaskDto> findTasksByUserId(int id) throws SQLException {
        List<Task> tasks = taskDAO.getTasksUserId(id, dbManager.getConnection());
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto(task.getId(), task.getName(), task.getTime());
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }

    @Override
    public void updateTime(int taskId, Time newTime) throws SQLException {
        taskDAO.updateTime(taskId, newTime, dbManager.getConnection());
    }

    @Override
    public void deleteTask(int taskId) throws SQLException {
        taskDAO.deleteTask(taskId, dbManager.getConnection());
    }

    @Override
    public List<TaskTableItem> findAllTasks(int offset, int limit, String orderBy, String search) throws SQLException{
        return taskDAO.findAllTasks(offset, limit, orderBy, search, dbManager.getConnection());
    }

    @Override
    public int getTaskCount(String search) throws SQLException {
        return taskDAO.getTaskCount(search, dbManager.getConnection());
    }

}

