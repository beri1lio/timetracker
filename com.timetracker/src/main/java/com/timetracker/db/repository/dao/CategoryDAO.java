package com.timetracker.db.repository.dao;

import com.timetracker.db.entity.Category;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class queries for categories.
 */
public class CategoryDAO {

    private static final Logger LOGGER = Logger.getLogger(CategoryDAO.class);

    /**
     * SQL queries.
     */
    private static final String SELECT_FROM_CATEGORY = "SELECT * FROM `category`;";
    private static final String SELECT_COUNT_FROM_CATEGORY = "SELECT COUNT(*) FROM category;";
    private static final String SELECT_FROM_CATEGORY_WHERE_ID = "SELECT * FROM `category` WHERE `id`=?;";
    private static final String SELECT_FROM_CATEGORY_WHERE_NAME = "SELECT * FROM `category` WHERE `name`=?;";
    private static final String SELECT_FROM_CATEGORY_PAGINATION = "SELECT * FROM category LIMIT ?, ? ;";
    private static final String INSERT_CATEGORY_QUERY = "INSERT INTO `category` (`name`) VALUES (?);";
    private static final String DELETE_CATEGORY = "DELETE FROM `category` WHERE `id`=?;";

    /**
     * Return category entity by using id.
     * @param id - id category;
     */
    public Category getCategory(int id, Connection connection) throws SQLException {
        Category category = null;
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_CATEGORY_WHERE_ID
                        , Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            category = new Category.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .build();
        }

        return category;
    }

    /**
     * Return category entity by using name.
     * @param name - name category;
     */
    public Category getCategory(String name, Connection connection) throws SQLException {
        Category category = null;
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_CATEGORY_WHERE_NAME
                        , Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            category = new Category.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .build();
        }

        return category;
    }

    /**
     * Return count categories.
     */
    public int getCategoryCount(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_COUNT_FROM_CATEGORY);

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    /**
     * Return all categories.
     */
    public List<Category> findAllCategories(Connection connection) throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_FROM_CATEGORY);
        while (resultSet.next()) {
            Category build = new Category.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .build();
            categoryList.add(build);
        }

        return categoryList;
    }

    /**
     *  Return all categories with a certain offset and data limit.
     * @param offset - offset
     * @param limit - limit
     */
    public List<Category> findAllCategories(int offset, int limit, Connection connection) throws SQLException   {
        List<Category> categoryList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_CATEGORY_PAGINATION);
        statement.setInt(1, offset);
        statement.setInt(2, limit);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Category build = new Category.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .build();
            categoryList.add(build);
        }
        return categoryList;
    }

    /**
     * Add new category.
     * @param category - new category.
     */
    public Category addCategory(Connection connection, Category category) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, category.getName());

        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        Category categoryWithId = null;
        if (generatedKeys.next()) {
            categoryWithId = new Category.Builder()
                    .withId(generatedKeys.getInt(1))
                    .withName(category.getName())
                    .build();
        }

        close(generatedKeys, statement);
        return categoryWithId;
    }

    /**
     * Delete category.
     * @param id - category id.
     */
    public void deleteCategory(int id, Connection connection) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(DELETE_CATEGORY);
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
