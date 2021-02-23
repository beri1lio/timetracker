package com.timetracker.servlet;

import com.timetracker.db.entity.User;
import com.timetracker.service.RegistrationService;
import com.timetracker.service.impl.RegistrationServiceImpl;
import com.timetracker.util.ValidationUtil;
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

    /**
     * Until the user is validated, registration will not work.
     * When registration is complete, you will be redirected to the home page.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String md5HexPassword = DigestUtils.md5Hex(password);

        boolean isNotEmptyLogin = ValidationUtil.isNotEmptyValidation(login, "login", "global.missingLoginError", req);
        boolean isNotEmptyName = ValidationUtil.isNotEmptyValidation(login, "name", "global.missingNameError", req);
        boolean isNotEmptyPass = ValidationUtil.isNotEmptyValidation(password, "password", "global.missingPasswordError", req);
        boolean isNotTooShortPass = ValidationUtil.isNotTooShortValidation(password, 5, "password", "global.shortPasswordError", req);
        if(!isNotEmptyLogin || !isNotEmptyName || !isNotEmptyPass || !isNotTooShortPass){
            resp.sendRedirect("/registration");
            return;
        }

        User user = new User.Builder()
                .withLogin(login)
                .withName(name)
                .withPassword(md5HexPassword)
                .build();

        try {
            registrationService.registrateUser(user);
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        }
    resp.sendRedirect("/");
    }


}
