package com.timetracker.service;

import com.timetracker.db.entity.Category;

import java.sql.SQLException;

public interface CategoryService {

    boolean newCategory(Category category) throws SQLException;
}
