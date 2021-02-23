package com.timetracker.servlet.category;

import com.timetracker.service.CategoriesService;
import com.timetracker.service.impl.CategoriesServiceImpl;
import com.timetracker.util.pagination.PaginationDataCountProvider;
import com.timetracker.util.pagination.PaginationDataDefaultOrderProvider;
import com.timetracker.util.pagination.PaginationDataProvider;
import com.timetracker.util.pagination.PaginationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * All categories.
 */
@WebServlet("/categories")
public class CategoriesServlet extends HttpServlet {

    private final CategoriesService categoriesService = new CategoriesServiceImpl();

    private final PaginationDataProvider dataProvider = new PaginationDataProvider() {
        @Override
        public List provideData(int offset, int limit, String orderBy, String search) throws SQLException {
            return categoriesService.findAllCategories(offset, 10);
        }
    };

    private final PaginationDataCountProvider dataCountProvider = new PaginationDataCountProvider() {
        @Override
        public int provideDataCount(String search) throws SQLException {
            return categoriesService.getCategoryCount();
        }
    };

    private final PaginationDataDefaultOrderProvider dataDefaultOrderProvider = new PaginationDataDefaultOrderProvider() {
        @Override
        public String provideDataOrder() {
            return "";
        }
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaginationUtil.executePagination(req, resp, dataProvider, dataCountProvider, dataDefaultOrderProvider);

        req.getRequestDispatcher("categories.jsp").forward(req, resp);
    }
}
