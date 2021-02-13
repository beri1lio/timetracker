package com.timetracker.servlet;

import com.timetracker.service.UserService;
import com.timetracker.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userID = req.getParameter("user-id");

        try {
            userService.deleteUser(Integer.parseInt(userID));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.sendRedirect("/users");
    }
}
