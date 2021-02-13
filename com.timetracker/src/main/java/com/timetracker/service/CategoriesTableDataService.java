package com.timetracker.service;

import com.timetracker.dto.CategoryDto;

import java.sql.SQLException;
import java.util.List;

public interface CategoriesTableDataService {

    List<CategoryDto> findAllCategory() throws SQLException;
}
