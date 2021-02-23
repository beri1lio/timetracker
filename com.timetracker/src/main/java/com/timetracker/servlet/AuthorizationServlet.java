package com.timetracker.servlet;


import com.timetracker.db.entity.User;
import com.timetracker.service.AuthorizationService;
import com.timetracker.service.impl.AuthorizationServiceImpl;
import com.timetracker.util.ValidationUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/authorization-user")
public class AuthorizationServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AuthorizationServlet.class);

    private AuthorizationService authorizationService = new AuthorizationServiceImpl();

    /**
     * Until the user is validated, authorization will not work.
     * When authorization is complete, you will be redirected to the page according to his role.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String md5HexPassword = DigestUtils.md5Hex(password);

        boolean isNotEmptyLogin = ValidationUtil.isNotEmptyValidation(login, "login", "global.missingLoginError", req);
        boolean isNotEmptyPass = ValidationUtil.isNotEmptyValidation(password, "password", "global.missingPasswordError", req);
        boolean isNotTooShortPass = ValidationUtil.isNotTooShortValidation(password, 5, "password", "global.shortPasswordError", req);
        if (!isNotEmptyLogin || !isNotEmptyPass || !isNotTooShortPass) {
            resp.sendRedirect("/authorization");
            return;
        }

        User user = new User.Builder()
                .withLogin(login)
                .withPassword(md5HexPassword)
                .build();

        User currentUser = null;
        try {
            currentUser = authorizationService.authorizeUser(user);
            HttpSession session = req.getSession();
            if (currentUser != null) {
                session.setAttribute("userID", currentUser.getId());
                session.setAttribute("userRole", currentUser.getRole().name());
                LOGGER.info("User " + currentUser.getId() + " is authorized.");
            }
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException.getMessage());
        }

        resp.sendRedirect("/");
    }
}
