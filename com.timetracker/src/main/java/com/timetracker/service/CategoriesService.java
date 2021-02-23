package com.timetracker.service;

import com.timetracker.db.entity.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface contains methods to process requests from controller to DAO layer.
 */
public interface CategoriesService {

    List<Category> findAllCategories() throws SQLException;

    List<Category> findAllCategories(int offset, int limit) throws SQLException;

    int getCategoryCount() throws SQLException;
}
