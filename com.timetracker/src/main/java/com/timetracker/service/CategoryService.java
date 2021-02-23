package com.timetracker.service;

import com.timetracker.db.entity.Category;

import java.sql.SQLException;

/**
 * The interface contains methods to process requests from controller to DAO layer.
 */
public interface CategoryService {

    boolean newCategory(Category category) throws SQLException;

    void deleteCategory(int parseInt) throws SQLException;
}
