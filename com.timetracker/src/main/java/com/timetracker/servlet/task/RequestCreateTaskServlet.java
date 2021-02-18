package com.timetracker.servlet.task;

import com.timetracker.db.entity.Status;
import com.timetracker.db.entity.Task;
import com.timetracker.service.TaskService;
import com.timetracker.service.impl.TaskServiceImpl;
import com.timetracker.servlet.AuthorizationServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/request-create-task")
public class RequestCreateTaskServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RequestCreateTaskServlet.class);
    private TaskService taskService = new TaskServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userID");
        String name = req.getParameter("taskName");
        String categoryID = req.getParameter("categoryID");

        Task task = new Task.Builder()
                .withName(name)
                .withUserId(userId)
                .withCategoryId(Integer.parseInt(categoryID))
                .withStatus(Status.NEW)
                .build();
        try{
            taskService.newTask(task);
        }catch (SQLException sqlException){
            LOGGER.error(sqlException.getMessage());
        }

        resp.sendRedirect("/profile");
    }
}
