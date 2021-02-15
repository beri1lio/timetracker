package com.timetracker.service.impl;

import com.timetracker.db.entity.Category;
import com.timetracker.db.repository.dao.CategoryDAO;
import com.timetracker.db.repository.dao.ConnectionPool;
import com.timetracker.service.CategoriesService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoriesServiceImpl implements CategoriesService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public List<Category> findAllCategory() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Category> allCategories = categoryDAO.findAllCategories(connection);
        connection.close();
        return allCategories;

    }
}
