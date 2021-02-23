package com.timetracker.servlet.task;

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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation of the time spent by the user.
 */
@WebServlet("/update-time")
public class UpdateTaskServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UpdateTaskServlet.class);

    private TaskService taskService = new TaskServiceImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskID = req.getParameter("task-id");
        String time = req.getParameter("time");

        Pattern pattern = Pattern.compile("^(?:(?:([01]?\\d|2[0-3]):)?([0-5]?\\d):)?([0-5]?\\d)$");
        Matcher matcher = pattern.matcher(time);
        boolean isTimeValid = matcher.find();
        ValidationUtil.validate(isTimeValid, "time", "global.missingTimeError", req);
        if (!isTimeValid) {
            resp.sendRedirect("/profile");
            return;
        }
        try {
            long ms = sdf.parse(time).getTime();
            taskService.updateTime(Integer.parseInt(taskID), new Time(ms));
        } catch (ParseException | SQLException e) {
            LOGGER.error(e.getMessage());
        }

        resp.sendRedirect("/profile");
    }
}
