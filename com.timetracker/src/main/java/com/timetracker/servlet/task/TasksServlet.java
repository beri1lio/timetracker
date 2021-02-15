package com.timetracker.servlet.task;

import com.timetracker.db.entity.Category;
import com.timetracker.db.entity.User;
import com.timetracker.service.CategoriesService;
import com.timetracker.service.TaskService;
import com.timetracker.service.UserService;
import com.timetracker.service.impl.CategoriesServiceImpl;
import com.timetracker.service.impl.TaskServiceImpl;
import com.timetracker.service.impl.UserServiceImpl;
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

@WebServlet("/tasks")
public class TasksServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private CategoriesService categoriesService = new CategoriesServiceImpl();
    private TaskService taskService = new TaskServiceImpl();

    private final PaginationDataProvider dataProvider = new PaginationDataProvider() {
        @Override
        public List provideData(int offset, int limit, String orderBy, String search) throws SQLException {
            return taskService.findAllTasks(offset, 10, orderBy, search);
        }
    };

    private final PaginationDataCountProvider dataCountProvider = new PaginationDataCountProvider() {
        @Override
        public int provideDataCount(String search) throws SQLException {
            return taskService.getTaskCount(search);
        }
    };

    private final PaginationDataDefaultOrderProvider dataDefaultOrderProvider = new PaginationDataDefaultOrderProvider() {
        @Override
        public String provideDataOrder() {
            return "task.name";
        }
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaginationUtil.executePagination(req, resp, dataProvider, dataCountProvider, dataDefaultOrderProvider);
        try{
            List<User> users = userService.findAllUser();
            List<Category> categories = categoriesService.findAllCategory();
            if(users != null){
                req.setAttribute("users", users);
            }
            if(categories != null){
                req.setAttribute("categories", categories);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        req.getRequestDispatcher("task-admin.jsp").forward(req, resp);
    }
}
