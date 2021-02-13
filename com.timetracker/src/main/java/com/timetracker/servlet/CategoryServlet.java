package com.timetracker.servlet;

import com.timetracker.db.entity.Category;
import com.timetracker.service.CategoryService;
import com.timetracker.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("category");

        Category category = new Category.Builder()
                .withName(name)
                .build();
        try{
            categoryService.newCategory(category);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        resp.sendRedirect("/tasks");
    }
}
