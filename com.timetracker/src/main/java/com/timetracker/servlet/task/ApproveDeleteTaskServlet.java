package com.timetracker.servlet.task;

import com.timetracker.service.TaskService;
import com.timetracker.service.impl.TaskServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/approve-delete-task")
public class ApproveDeleteTaskServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ApproveDeleteTaskServlet.class);

    private TaskService taskService = new TaskServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskID = req.getParameter("task-id");

        try {
            taskService.deleteTask(Integer.parseInt(taskID));
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        }

        resp.sendRedirect("/approve-tasks");
    }
}
