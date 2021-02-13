package com.timetracker.servlet.task;

import com.timetracker.service.TaskService;
import com.timetracker.service.impl.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/update-time")
public class UpdateTaskServlet extends HttpServlet {

    private TaskService taskService = new TaskServiceImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskID = req.getParameter("task-id");
        String time = req.getParameter("time");

        try {
            long ms = sdf.parse(time).getTime();
            taskService.updateTime(Integer.parseInt(taskID), new Time(ms));
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/profile");
    }
}
