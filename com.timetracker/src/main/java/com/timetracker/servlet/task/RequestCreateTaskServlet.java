package com.timetracker.servlet.task;

import com.timetracker.db.entity.Status;
import com.timetracker.db.entity.Task;
import com.timetracker.service.TaskService;
import com.timetracker.service.impl.TaskServiceImpl;
import com.timetracker.util.ValidationUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * A request to create a task from a user.
 */
@WebServlet("/request-create-task")
public class RequestCreateTaskServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RequestCreateTaskServlet.class);
    private TaskService taskService = new TaskServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userID");
        String name = req.getParameter("taskName");
        String categoryID = req.getParameter("categoryID");

        boolean isNotEmptyName = ValidationUtil.isNotEmptyValidation(name, "taskName", "global.missingTaskNameError", req);
        boolean isNotEmptyCategoryID = ValidationUtil.isNotEmptyValidation(categoryID, "categoryID", "global.missingCategoryError", req);
        if(!isNotEmptyName || !isNotEmptyCategoryID){
            resp.sendRedirect("/profile");
            return;
        }

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
