package com.timetracker.db.repository.dao;

import com.timetracker.db.entity.Role;
import com.timetracker.db.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    /**
     * SQL queries.
     */
    private static final String SELECT_FROM_USER = "SELECT * FROM user;";
    private static final String SELECT_FROM_USER_PAGINATION = "SELECT * FROM user WHERE `name` LIKE ? OR `login` LIKE ? ORDER BY `%s` LIMIT ?, ? ;";
    private static final String SELECT_FROM_USER_WHERE_ID = "SELECT * FROM `user` WHERE `id`=?;";
    private static final String SELECT_FROM_USER_WHERE_LOGIN = "SELECT * FROM `user` WHERE `login`=?;";
    private static final String SELECT_LOGIN_AND_PASSWORD = "SELECT * FROM `user` WHERE `login`=? AND `password`=?;";
    private static final String SELECT_COUNT_FROM_USER = "SELECT COUNT(*) FROM user WHERE `name` LIKE ? OR `login` LIKE ? ;";
    private static final String INSERT_USER_QUERY = "INSERT INTO `user` (`name`, `login`, `password`) VALUES (?, ?, ?);";
    private static final String DELETE_USER = "DELETE FROM `user` WHERE `id`=?;";

    /**
     * Get User with id
     */
    public User getUser(int id, Connection connection) throws SQLException{
        User user = null;
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_USER_WHERE_ID,
                        Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);

        return getUser(user, statement);
    }

    /**
     * Build and return user.
     */
    private User getUser(User user, PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            user = new User.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withLogin(resultSet.getString("login"))
                    .withPassword(resultSet.getString("password"))
                    .withRole(Role.valueOf(resultSet.getString("role")))
                    .build();
        }

        return user;
    }

    /**
     * Get User with login
     * @param login - user login.
     */
    public User getUser(String login, Connection connection) throws SQLException{
        User user = null;
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_USER_WHERE_LOGIN,
                        Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, login);

        return getUser(user, statement);
    }

    /**
     * Get User with login and password.
     * @param login - user login.
     * @param password - user password.
     */
    public User getUser(String login, String password, Connection connection) throws SQLException{
        User user = null;
        PreparedStatement statement = connection
                .prepareStatement(SELECT_LOGIN_AND_PASSWORD,
                        Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, login);
        statement.setString(2, password);

        return getUser(user, statement);
    }

    /**
     * Return User count with key word.
     * @param search - key word.
     */
    public int getUserCount(String search, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_COUNT_FROM_USER, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, "%" + search + "%");
        statement.setString(2, "%" + search + "%");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    /**
     * Return all users.
     */
    public List<User> findAllUsers(Connection connection) throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SELECT_FROM_USER);
        while (resultSet.next()) {
            User build = new User.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withLogin(resultSet.getString("login"))
                    .withPassword(resultSet.getString("password"))
                    .withRole(Role.valueOf(resultSet.getString("role")))
                    .build();
            userList.add(build);
        }

        return userList;
    }

    /**
     * Return all users with a certain offset, data limit and word sorting.
     * @param offset - offset.
     * @param limit - data limit.
     * @param orderBy - word sorting.
     * @param search - key word.
     */
    public List<User> findAllUsers(int offset, int limit, String orderBy, String search, Connection connection) throws SQLException {
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(String.format(SELECT_FROM_USER_PAGINATION, orderBy));
        statement.setString(1, "%" + search + "%");
        statement.setString(2, "%" + search + "%");
        statement.setInt(3, offset);
        statement.setInt(4, limit);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            User build = new User.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withLogin(resultSet.getString("login"))
                    .withPassword(resultSet.getString("password"))
                    .withRole(Role.valueOf(resultSet.getString("role")))
                    .build();
            userList.add(build);
        }

        return userList;
    }

    /**
     * Add new User
     */
    public User addUser(User user, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getName());
        statement.setString(2, user.getLogin());
        statement.setString(3, user.getPassword());

        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        User userWithId = null;
        if (generatedKeys.next()) {
            userWithId = new User.Builder()
                    .withId(generatedKeys.getInt(1))
                    .withName(user.getName())
                    .withLogin(user.getLogin())
                    .withPassword(user.getPassword())
                    .withRole(user.getRole())
                    .build();
        }

        close(generatedKeys, statement);
        return userWithId;
    }

    /**
     * Delete user with id.
     * @param id - id user.
     */
    public void deleteUser(int id, Connection connection) throws  SQLException{
        PreparedStatement statement = connection
                .prepareStatement(DELETE_USER);
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
