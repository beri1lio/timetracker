package com.timetracker.service.impl;

import com.timetracker.db.entity.Category;
import com.timetracker.db.repository.dao.CategoryDAO;
import com.timetracker.db.repository.dao.ConnectionPool;
import com.timetracker.service.CategoryService;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides implementation of all {@code CategoryServiceImpl} interface methods.
 */
public class CategoryServiceImpl implements CategoryService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public void deleteCategory(int parseInt) throws SQLException {
        Connection connection = connectionPool.getConnection();
        categoryDAO.deleteCategory(parseInt, connection);
        connection.close();
    }

    @Override
    public boolean newCategory(Category category) throws SQLException {
        Connection connection = connectionPool.getConnection();
        Category currentCategory = categoryDAO.getCategory(category.getName(), connection);

        if(currentCategory != null){
            return false;
        }

        Category newCategory = categoryDAO.addCategory(connection, category);
        connection.close();
        return newCategory != null;
    }
}
