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

@WebServlet("/approve-tasks")
public class ApproveTaskServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ApproveTaskServlet.class);
    private TaskService taskService = new TaskServiceImpl();

    /**
     * Method get Unapproved tasks(NEW, DELETED) and set in data.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("data", taskService.getUnapprovedTasks());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        req.getRequestDispatcher("/approve-task-admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskID = req.getParameter("task-id");

        try {
            taskService.updateStatus(Integer.parseInt(taskID), Status.APPROVED);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        resp.sendRedirect("/approve-tasks");
    }
}
