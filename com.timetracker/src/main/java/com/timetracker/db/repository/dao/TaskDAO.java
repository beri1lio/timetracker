package com.timetracker.db.repository.dao;

import com.timetracker.db.entity.Status;
import com.timetracker.db.entity.Task;
import com.timetracker.db.entity.TaskTableItem;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class queries for task.
 */
public class TaskDAO {

    private static final Logger LOGGER = Logger.getLogger(TaskDAO.class);

    /**
     * SQL queries.
     */
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
    private static final String SELECT_FROM_TASK_WHERE_USER_ID = "SELECT * FROM `task` WHERE `user_id`=?;";
    private static final String SELECT_FROM_TASK_WHERE_NAME = "SELECT * FROM `task` WHERE `name`=?;";
    private static final String SELECT_FROM_TASK_WHERE_CATEGORY_ID = "SELECT * FROM `task` WHERE `category_id`=?;";
    private static final String SELECT_UNAPPROVED_TASKS = "SELECT task.id, task.name, category.name `category_name`, task.time, task.status, user.name `user_name` FROM task " +
            "JOIN `category` ON task.category_id=category.id " +
            "JOIN `user` ON task.user_id=user.id " +
            "WHERE task.status = 'NEW' OR task.status = 'DELETED'";
    private static final String INSERT_TASK_QUERY = "INSERT INTO `task` (`name`, `user_id`, `category_id`, `status`) VALUES(?, ?, ?, ?);";
    private static final String UPDATE_TIME = "UPDATE `timetracker`.`task` SET `time`=? WHERE  `id`=?;";
    private static final String UPDATE_STATUS = "UPDATE `timetracker`.`task` SET `status`=? WHERE  `id`=?;";
    private static final String DELETE_TASK = "DELETE FROM `task` WHERE `id`=?";

    /**
     * Return task with id.
     * @param id - task id.
     */
    public Task getTaskId(int id, Connection connection) throws SQLException{
        return getTask(id, SELECT_FROM_TASK_WHERE_ID, connection);
    }

    /**
     * Return task with user id.
     * @param userID - user id.
     */
    public Task getTaskUserId(int userID, Connection connection) throws SQLException{
        return getTask(userID, SELECT_FROM_TASK_WHERE_USER_ID, connection);
    }

    /**
     * Return list of tasks with user id.
     * @param userID - user id.
     */
    public List<Task> getTasksUserId(int userID, Connection connection) throws SQLException{
        return getTasks(userID, connection, SELECT_FROM_TASK_WHERE_USER_ID);
    }

    /**
     * Return list of tasks with user id or category id.
     * @param id - task id.
     * @param sql - sql query that shows the task calculated for a certain user or category.
     */
    private List<Task> getTasks(int id, Connection connection, String sql) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        PreparedStatement statement = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        return getTasks(taskList, resultSet);
    }

    /**
     * Build task with result set. And return list of tasks.
     * @param taskList - list of tasks.
     */
    private List<Task> getTasks(List<Task> taskList, ResultSet resultSet) throws SQLException {
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

    /**
     * Return list of tasks with category id.
     * @param categoryID - category id.
     */
    public List<Task> getTasksCategoryId(int categoryID, Connection connection) throws SQLException{
        return getTasks(categoryID, connection, SELECT_FROM_TASK_WHERE_CATEGORY_ID);
    }

    /**
     * Return task with name.
     * @param name - name task.
     */
    public Task getTask(String name, Connection connection) throws SQLException{
        Task task = null;
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TASK_WHERE_NAME,Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        return getTask(task, statement);
    }

    /**
     * Build and return task.
     * @param task - task.
     */
    private Task getTask(Task task, PreparedStatement statement) throws SQLException {
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

    /**
     * Return task.
     * @param id - id.
     * @param sql - sql query that uses the id to determine the desired query.
     */
    public Task getTask(int id, String sql, Connection connection) throws SQLException{
        Task task = null;
        PreparedStatement statement = connection
                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);

        return getTask(task, statement);
    }

    /**
     * Return task table with category name and user name.
     */
    private List<TaskTableItem> getTaskTableItems(List<TaskTableItem> taskList, ResultSet resultSet) throws SQLException {
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

    /**
     * Return all tasks with a certain offset and data limit.
     * @param userID - user id.
     */
    public List<Task> getTasksUserId(int userID, int offset, int limit, Connection connection) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_TASKS_BY_USER);
        statement.setInt(1, userID);
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

    /**
     * Return count tasks with key word.
     * @param search - key word.
     */
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

    /**
     * Return all tasks with id user.
     * @param userID - user id.
     */
    public int getTaskCount(int userID, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_COUNT_TASKS_FROM_USER, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, userID);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    /**
     * Return list with the unapproved tasks.
     */
    public List<TaskTableItem> getUnapprovedTasks(Connection connection) throws SQLException {
        List<TaskTableItem> tasks = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_UNAPPROVED_TASKS);

        return getTaskTableItems(tasks, resultSet);
    }

    /**
     * Return all tasks.
     */
    public List<Task> findAllTasks(Connection connection) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_FROM_TASK);
        return getTasks(taskList, resultSet);
    }

    /**
     * Return all task table with user name and category name. With a certain offset,
     * data limit and word sorting.
     * @param offset - offset.
     * @param limit - data limit.
     * @param orderBy - word sorting.
     * @param search - key word.
     */
    public List<TaskTableItem> findAllTasks(int offset, int limit, String orderBy, String search, Connection connection) throws SQLException {
        List<TaskTableItem> taskList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(String.format(SELECT_FROM_TASK_PAGINATION, orderBy));
        statement.setString(1, "%" + search + "%");
        statement.setString(2, "%" + search + "%");
        statement.setString(3, "%" + search + "%");
        statement.setInt(4, offset);
        statement.setInt(5, limit);
        ResultSet resultSet = statement.executeQuery();
        return getTaskTableItems(taskList, resultSet);
    }

    /**
     * Update time in the task.
     * @param id - id task.
     * @param time - time.
     */
    public void updateTime(int id, Time time, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(UPDATE_TIME);
        statement.setString(1, String.valueOf(time));
        statement.setInt(2, id);

        statement.executeUpdate();
        close(statement);
    }

    /**
     * Updatae status in the task.
     * @param id - id task.
     * @param status - status task.
     */
    public void updateStatus(int id, Status status, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS);
        statement.setString(1, status.name());
        statement.setInt(2, id);

        statement.executeUpdate();
        close(statement);
    }

    /**
     * Add new task.
     */
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

        close(generatedKeys, statement);
        return taskWithId;
    }

    /**
     * Delete task.
     */
    public void deleteTask(int id, Connection connection) throws SQLException{
        PreparedStatement statement = connection
                .prepareStatement(DELETE_TASK);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    /**
     * Closes a statement object.
     */
    private void close(Statement statement){
        if(statement != null){
            try{
                statement.close();
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
            }
        }
    }

    /**
     * Closes a result set object.
     */
    private void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
            }
        }
    }

    /**
     * Closes resources.
     */
    private void close(ResultSet resultSet, Statement statement){
        close(resultSet);
        close(statement);
    }
}
