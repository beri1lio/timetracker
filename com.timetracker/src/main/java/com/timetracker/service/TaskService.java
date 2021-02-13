package com.timetracker.service;

import com.timetracker.db.entity.TaskTableItem;
import com.timetracker.dto.TaskDto;
import com.timetracker.db.entity.Task;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface TaskService {

    boolean newTask(Task task) throws SQLException;

    List<TaskDto> findTasksByUserId(int id) throws SQLException;

    void updateTime(int taskId, Time newTime) throws SQLException;

    void deleteTask(int taskId) throws SQLException;

    List<TaskTableItem> findAllTasks(int offset, int limit, String orderBy, String search) throws SQLException;

    int getTaskCount(String search) throws SQLException;
}
