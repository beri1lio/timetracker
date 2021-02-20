package com.timetracker.servlet.category;

import com.timetracker.db.entity.Category;
import com.timetracker.service.CategoryService;
import com.timetracker.service.impl.CategoryServiceImpl;
import com.timetracker.util.ValidationUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/category")
public class CreateCategoryServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateCategoryServlet.class);

    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("category");


        boolean isNotEmptyName = ValidationUtil.isNotEmptyValidation(name, "category", "global.missingCategoryError", req);
        if(!isNotEmptyName){
            resp.sendRedirect("/tasks");
            return;
        }

        Category category = new Category.Builder()
                .withName(name)
                .build();
        try{
            categoryService.newCategory(category);
        }catch (SQLException sqlException){
            LOGGER.error(sqlException.getMessage());
        }

        resp.sendRedirect("/tasks");
    }
}
