package com.timetracker.service.impl;

import com.timetracker.db.entity.Category;
import com.timetracker.db.repository.dao.CategoryDAO;
import com.timetracker.db.repository.dao.DBManager;
import com.timetracker.service.CategoryService;

import java.sql.SQLException;

public class CategoryServiceImpl implements CategoryService {

    private DBManager dbManager = DBManager.getInstance();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public boolean newCategory(Category category) throws SQLException {
        Category currentCategory = categoryDAO.getCategory(category.getName(), dbManager.getConnection());

        if(currentCategory != null){
            return false;
        }

        return categoryDAO.addCategory(dbManager.getConnection(), category) != null;
    }
}
