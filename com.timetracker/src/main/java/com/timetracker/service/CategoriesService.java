package com.timetracker.service;

import com.timetracker.db.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoriesService {

    List<Category> findAllCategory() throws SQLException;
}
