package com.timetracker.servlet;

import com.timetracker.dto.TaskDto;
import com.timetracker.service.TaskService;
import com.timetracker.service.UserAdminService;
import com.timetracker.service.impl.TaskServiceImpl;
import com.timetracker.service.impl.UserAdminServiceImpl;

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

    private UserAdminService userAdminService = new UserAdminServiceImpl();
    private TaskService taskService = new TaskServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int userID = (int) req.getSession().getAttribute("userID");
            List<TaskDto> userTableData = taskService.findTasksByUserId(userID);
            req.setAttribute("userTableData", userTableData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}
