package com.timetracker.servlet.task;

import com.timetracker.db.entity.Status;
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

@WebServlet("/request-delete-task")
public class RequestDeleteTaskServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RequestDeleteTaskServlet.class);

    private TaskService taskService = new TaskServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskID = req.getParameter("task-id");

        try {
            taskService.updateStatus(Integer.parseInt(taskID), Status.DELETED);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        resp.sendRedirect("/profile");
    }
}
