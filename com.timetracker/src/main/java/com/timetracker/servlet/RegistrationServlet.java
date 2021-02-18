package com.timetracker.servlet;

import com.timetracker.db.entity.User;
import com.timetracker.service.RegistrationService;
import com.timetracker.service.impl.RegistrationServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registration-user")
public class RegistrationServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);

    private RegistrationService registrationService = new RegistrationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String md5HexPassword = DigestUtils.md5Hex(password);

        User user = new User.Builder()
                .withLogin(login)
                .withName(name)
                .withPassword(md5HexPassword)
                .build();

        try {
            registrationService.registrateNewUser(user);
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        }
    resp.sendRedirect("/");
    }


}
