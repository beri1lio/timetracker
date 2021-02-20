package com.timetracker.servlet.profile;

import com.timetracker.db.entity.Category;
import com.timetracker.service.CategoriesService;
import com.timetracker.service.TaskService;
import com.timetracker.service.impl.CategoriesServiceImpl;
import com.timetracker.service.impl.TaskServiceImpl;
import com.timetracker.util.pagination.PaginationDataCountProvider;
import com.timetracker.util.pagination.PaginationDataDefaultOrderProvider;
import com.timetracker.util.pagination.PaginationDataProvider;
import com.timetracker.util.pagination.PaginationUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/profile")
public class ProfileUserServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProfileUserServlet.class);
    private CategoriesService categoriesService = new CategoriesServiceImpl();
    private TaskService taskService = new TaskServiceImpl();

    private final PaginationDataDefaultOrderProvider dataDefaultOrderProvider = new PaginationDataDefaultOrderProvider() {
        @Override
        public String provideDataOrder() {
            return "";
        }
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final int userID = (int) req.getSession().getAttribute("userID");

        PaginationDataProvider dataProvider = new PaginationDataProvider() {
            @Override
            public List provideData(int offset, int limit, String orderBy, String search) throws SQLException {
                return taskService.findTasksByUserId(userID, offset, 10);
            }
        };

        PaginationDataCountProvider dataCountProvider = new PaginationDataCountProvider() {
            @Override
            public int provideDataCount(String search) throws SQLException {
                return taskService.getTaskCount(userID);
            }
        };

        PaginationUtil.executePagination(req, resp, dataProvider, dataCountProvider, dataDefaultOrderProvider);

        try{
            List<Category> categories = categoriesService.findAllCategory();
            if(categories != null){
                req.setAttribute("categories", categories);
            }
        } catch (SQLException throwables){
            LOGGER.error(throwables);
        }


        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}
