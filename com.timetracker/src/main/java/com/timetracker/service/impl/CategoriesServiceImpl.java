package com.timetracker.service.impl;

import com.timetracker.db.entity.Category;
import com.timetracker.db.repository.dao.CategoryDAO;
import com.timetracker.db.repository.dao.DBManager;
import com.timetracker.service.CategoriesService;

import java.sql.SQLException;
import java.util.List;

public class CategoriesServiceImpl implements CategoriesService {

    private DBManager dbManager = DBManager.getInstance();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public List<Category> findAllCategory() throws SQLException {
        return categoryDAO.findAllCategories(dbManager.getConnection());
    }
}
