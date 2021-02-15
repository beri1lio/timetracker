package com.timetracker.service;

import com.timetracker.db.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoriesService {

    List<Category> findAllCategory() throws SQLException;

    List<Category> findAllCategory(int offset, int limit) throws SQLException;

    int getCategoryCount() throws SQLException;
}
