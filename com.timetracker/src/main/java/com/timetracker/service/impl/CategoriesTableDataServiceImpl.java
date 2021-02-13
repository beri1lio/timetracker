package com.timetracker.service.impl;

import com.timetracker.dto.CategoryDto;
import com.timetracker.dto.TaskDto;
import com.timetracker.db.entity.Category;
import com.timetracker.db.entity.Task;
import com.timetracker.db.entity.User;
import com.timetracker.db.repository.dao.CategoryDAO;
import com.timetracker.db.repository.dao.DBManager;
import com.timetracker.db.repository.dao.TaskDAO;
import com.timetracker.db.repository.dao.UserDAO;
import com.timetracker.service.CategoriesTableDataService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesTableDataServiceImpl implements CategoriesTableDataService {

    private DBManager dbManager = DBManager.getInstance();
    private TaskDAO taskDAO = new TaskDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private UserDAO userDAO = new UserDAO();

    @Override
    public List<CategoryDto> findAllCategory() throws SQLException {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> list = categoryDAO.findAllCategories(dbManager.getConnection());

        for (Category category : list) {
            List<Task> tasks  = taskDAO.getTasksCategoryId(category.getId(), dbManager.getConnection());
            List<TaskDto> taskDtos = new ArrayList<>();
            for (Task task : tasks) {
                User user = userDAO.getUser(task.getUserId(), dbManager.getConnection());
                TaskDto taskDto = new TaskDto(task.getId(), task.getName(), task.getTime(), user.getName());
                taskDtos.add(taskDto);
            }
            CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName(), taskDtos);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }
}
