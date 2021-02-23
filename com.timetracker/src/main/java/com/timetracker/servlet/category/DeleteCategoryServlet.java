package com.timetracker.servlet.category;

import com.timetracker.service.CategoryService;
import com.timetracker.service.impl.CategoryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Deleting a category.
 */
@WebServlet("/delete-category")
public class DeleteCategoryServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DeleteCategoryServlet.class);

    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryID = req.getParameter("category-id");

        try {
            categoryService.deleteCategory(Integer.parseInt(categoryID));
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        resp.sendRedirect("/categories");
    }
}
