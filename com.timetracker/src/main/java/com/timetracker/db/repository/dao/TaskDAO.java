package com.timetracker.db.repository.dao;

import com.timetracker.db.entity.Status;
import com.timetracker.db.entity.Task;
import com.timetracker.db.entity.TaskTableItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    //id - name - time - user_id - category_id

    private static final String INSERT_TASK_QUERY = "INSERT INTO `task` (`name`, `user_id`, `category_id`, `status`) VALUES(?, ?, ?, ?);";
    private static final String SELECT_FROM_TASK = "SELECT * FROM `task`;";
    private static final String SELECT_FROM_TASK_PAGINATION = "SELECT task.id, task.name, category.name `category_name`, task.time, status, user.name `user_name` FROM task " +
            "JOIN `category` ON task.category_id=category.id " +
            "JOIN `user` ON task.user_id=user.id " +
            "WHERE task.name LIKE ? OR category.name LIKE ? OR user.name LIKE ? " +
            "ORDER BY %s " +
            "LIMIT ?, ?;";
    private static final String SELECT_TASKS_BY_USER = "SELECT * FROM task WHERE task.user_id=? LIMIT ?, ?;";
    private static final String SELECT_COUNT_FROM_TASK = "SELECT COUNT(*) FROM task " +
            "JOIN `category` ON task.category_id=category.id " +
            "JOIN `user` ON task.user_id=user.id " +
            "WHERE task.name LIKE ? OR category.name LIKE ? OR user.name LIKE ? ;";
    private static final String SELECT_COUNT_TASKS_FROM_USER = "SELECT COUNT(name) FROM task WHERE task.user_id=?;";
    private static final String SELECT_FROM_TASK_WHERE_ID = "SELECT * FROM `task` WHERE `id`=?;";
    private static final String SELECT_FROM_TASK_WHERE_NAME = "SELECT * FROM `task` WHERE `name`=?;";
    private static final String SELECT_FROM_TASK_WHERE_USER_ID = "SELECT * FROM `task` WHERE `user_id`=?;";
    private static final String SELECT_FROM_TASK_WHERE_CATEGORY_ID = "SELECT * FROM `task` WHERE `category_id`=?;";
    private static final String SELECT_UNAPPROVED_TASKS = "SELECT task.id, task.name, category.name `category_name`, task.time, task.status, user.name `user_name` FROM task " +
            "JOIN `category` ON task.category_id=category.id " +
            "JOIN `user` ON task.user_id=user.id " +
            "WHERE task.status = 'NEW' OR task.status = 'DELETED'";
    private static final String DELETE_TASK = "DELETE FROM `task` WHERE `id`=?";
    private static final String UPDATE_TIME = "UPDATE `timetracker`.`task` SET `time`=? WHERE  `id`=?;";
    private static final String UPDATE_STATUS = "UPDATE `timetracker`.`task` SET `status`=? WHERE  `id`=?;";

    public Task getTaskId(int id, Connection connection) throws SQLException{
        return getTask(id, SELECT_FROM_TASK_WHERE_ID, connection);
    }

    public Task getTaskUserId(int id, Connection connection) throws SQLException{
        return getTask(id, SELECT_FROM_TASK_WHERE_USER_ID, connection);
    }

    public List<Task> getTasksUserId(int id, Connection connection) throws SQLException{
        List<Task> taskList = new ArrayList<>();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TASK_WHERE_USER_ID ,Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Task build = new Task.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withTime(Time.valueOf(resultSet.getString("time")))
                    .withUserId(resultSet.getInt("user_id"))
                    .withCategoryId(resultSet.getInt("category_id"))
                    .withStatus(Status.valueOf(resultSet.getString("status")))
                    .build();
            taskList.add(build);
        }
        return taskList;
    }

    public List<Task> getTasksCategoryId(int id, Connection connection) throws SQLException{
        List<Task> taskList = new ArrayList<>();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TASK_WHERE_CATEGORY_ID ,Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Task build = new Task.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withTime(Time.valueOf(resultSet.getString("time")))
                    .withUserId(resultSet.getInt("user_id"))
                    .withCategoryId(resultSet.getInt("category_id"))
                    .withStatus(Status.valueOf(resultSet.getString("status")))
                    .build();
            taskList.add(build);
        }
        return taskList;
    }

    public Task getTask(String name, Connection connection) throws SQLException{
        Task task = null;
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TASK_WHERE_NAME,Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            task = new Task.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withTime(Time.valueOf(resultSet.getString("time")))
                    .withUserId(resultSet.getInt("user_id"))
                    .withCategoryId(resultSet.getInt("category_id"))
                    .withStatus(Status.valueOf(resultSet.getString("status")))
                    .build();
        }

        return task;
    }

    public Task getTask(int id, String sql, Connection connection) throws SQLException{
        Task task = null;
        PreparedStatement statement = connection
                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            task = new Task.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withTime(Time.valueOf(resultSet.getString("time")))
                    .withUserId(resultSet.getInt("user_id"))
                    .withCategoryId(resultSet.getInt("category_id"))
                    .withStatus(Status.valueOf(resultSet.getString("status")))
                    .build();
        }

        return task;
    }

    public List<Task> findAllTasks(Connection connection) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_FROM_TASK);
        while (resultSet.next()){
            Task build = new Task.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withTime(Time.valueOf(resultSet.getString("time")))
                    .withUserId(resultSet.getInt("user_id"))
                    .withCategoryId(resultSet.getInt("category_id"))
                    .withStatus(Status.valueOf(resultSet.getString("status")))
                    .build();
            taskList.add(build);
        }
        return taskList;
    }

    public List<TaskTableItem> findAllTasks(int offset, int limit, String orderBy, String search, Connection connection) throws SQLException {
        List<TaskTableItem> taskList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(String.format(SELECT_FROM_TASK_PAGINATION, orderBy));
        statement.setString(1, "%" + search + "%");
        statement.setString(2, "%" + search + "%");
        statement.setString(3, "%" + search + "%");
        statement.setInt(4, offset);
        statement.setInt(5, limit);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            TaskTableItem build = new TaskTableItem.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withCategoryString(resultSet.getString("category_name"))
                    .withTime(Time.valueOf(resultSet.getString("time")))
                    .withUserString(resultSet.getString("user_name"))
                    .withStatus(Status.valueOf(resultSet.getString("status")))
                    .build();
            taskList.add(build);
        }
        return taskList;
    }

    public List<Task> getTasksUserId(int id, int offset, int limit, Connection connection) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_TASKS_BY_USER);
        statement.setInt(1, id);
        statement.setInt(2, offset);
        statement.setInt(3, limit);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Task build = new Task.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withTime(resultSet.getTime("time"))
                    .withUserId(resultSet.getInt("user_id"))
                    .withCategoryId(resultSet.getInt("category_id"))
                    .withStatus(Status.valueOf(resultSet.getString("status")))
                    .build();
            taskList.add(build);
        }
        return taskList;
    }

    public void deleteTask(int id, Connection connection) throws SQLException{
        PreparedStatement statement = connection
                .prepareStatement(DELETE_TASK);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void updateTime(int id, Time time, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(UPDATE_TIME);
        statement.setString(1, String.valueOf(time));
        statement.setInt(2, id);

        statement.executeUpdate();
        statement.close();
    }

    public Task addTask(Connection connection, Task task) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_TASK_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, task.getName());
        statement.setInt(2, task.getUserId());
        statement.setInt(3, task.getCategoryId());
        statement.setString(4, task.getStatus().name());

        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        Task taskWithId = null;
        if (generatedKeys.next()) {
            taskWithId = new Task.Builder()
                    .withId(generatedKeys.getInt(1))
                    .withName(task.getName())
                    .withTime(task.getTime())
                    .withUserId(task.getUserId())
                    .withCategoryId(task.getCategoryId())
                    .withStatus(task.getStatus())
                    .build();
        }

        generatedKeys.close();
        statement.close();
        return taskWithId;
    }

    public int getTaskCount(String search, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_COUNT_FROM_TASK, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, "%" + search + "%");
        statement.setString(2, "%" + search + "%");
        statement.setString(3, "%" + search + "%");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    public int getTaskCount(int userID, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_COUNT_TASKS_FROM_USER, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, userID);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    public List<TaskTableItem> getUnapprovedTasks(Connection connection) throws SQLException {
        List<TaskTableItem> tasks = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_UNAPPROVED_TASKS);

        while (resultSet.next()){
            TaskTableItem build = new TaskTableItem.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withCategoryString(resultSet.getString("category_name"))
                    .withTime(Time.valueOf(resultSet.getString("time")))
                    .withUserString(resultSet.getString("user_name"))
                    .withStatus(Status.valueOf(resultSet.getString("status")))
                    .build();
            tasks.add(build);
        }
        return tasks;
    }

    public void updateStatus(int taskId, Status status, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS);
        statement.setString(1, status.name());
        statement.setInt(2, taskId);

        statement.executeUpdate();
        statement.close();
    }
}
