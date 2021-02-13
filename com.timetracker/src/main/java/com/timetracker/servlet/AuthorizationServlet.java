package com.timetracker.servlet;

import com.timetracker.db.entity.User;
import com.timetracker.service.AuthorizationService;
import com.timetracker.service.impl.AuthorizationServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

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

    private AuthorizationService authorizationService = new AuthorizationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String md5HexPassword = DigestUtils.md5Hex(password);

        User user = new User.Builder()
                .withLogin(login)
                .withPassword(md5HexPassword)
                .build();

        try{
            User currentUser = authorizationService.authorizateUser(user);
            HttpSession session = req.getSession();
            if (currentUser != null) {
                session.setAttribute("userID", currentUser.getId());
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        resp.sendRedirect("/");
    }
}
