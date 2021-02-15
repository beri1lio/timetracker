package com.timetracker.servlet.category;

import com.timetracker.service.CategoryService;
import com.timetracker.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-category")
public class DeleteCategoryrServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryID = req.getParameter("category-id");

        try {
            categoryService.deleteCategory(Integer.parseInt(categoryID));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.sendRedirect("/categories");
    }
}
