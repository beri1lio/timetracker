package com.timetracker.service;

import com.timetracker.db.entity.Status;
import com.timetracker.db.entity.Task;
import com.timetracker.db.entity.TaskTableItem;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface TaskService {

    boolean newTask(Task task) throws SQLException;

    List<Task> findTasksByUserId(int id, int offset, int limit) throws SQLException;

    List<TaskTableItem> getUnapprovedTasks() throws SQLException;

    void updateTime(int taskId, Time newTime) throws SQLException;

    void deleteTask(int taskId) throws SQLException;

    List<TaskTableItem> findAllTasks(int offset, int limit, String orderBy, String search) throws SQLException;

    int getTaskCount(String search) throws SQLException;

    int getTaskCount(int userId) throws SQLException;

    void updateStatus(int taskId, Status status) throws SQLException;
}
