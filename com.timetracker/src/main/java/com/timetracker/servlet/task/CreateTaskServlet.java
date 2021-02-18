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

@WebServlet("/task")
public class CreateTaskServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateTaskServlet.class);

    private TaskService taskService = new TaskServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("taskName");
        String userID = req.getParameter("userID");
        String categoryID = req.getParameter("categoryID");

        Task task = new Task.Builder()
                .withName(name)
                .withUserId(Integer.parseInt(userID))
                .withCategoryId(Integer.parseInt(categoryID))
                .withStatus(Status.APPROVED)
                .build();
        try{
            taskService.newTask(task);
        }catch (SQLException sqlException){
            LOGGER.error(sqlException.getMessage());
        }

        resp.sendRedirect("/tasks");
    }
}
