package com.timetracker.service.impl;

import com.timetracker.db.entity.Category;
import com.timetracker.db.repository.dao.CategoryDAO;
import com.timetracker.db.repository.dao.ConnectionPool;
import com.timetracker.service.CategoriesService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Provides implementation of all {@code CategoriesServiceImpl} interface methods.
 */
public class CategoriesServiceImpl implements CategoriesService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public List<Category> findAllCategories() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Category> allCategories = categoryDAO.findAllCategories(connection);
        connection.close();
        return allCategories;
    }

    @Override
    public List<Category> findAllCategories(int offset, int limit) throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Category> allCategories = categoryDAO.findAllCategories(offset, limit, connection);
        connection.close();
        return allCategories;

    }

    @Override
    public int getCategoryCount() throws SQLException {
        Connection connection = connectionPool.getConnection();
        int count = categoryDAO.getCategoryCount(connection);
        connection.close();
        return count;
    }
}
