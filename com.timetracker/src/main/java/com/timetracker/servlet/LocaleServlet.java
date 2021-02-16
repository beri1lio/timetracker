package com.timetracker.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("locale") != null) {
            String locale = req.getParameter("locale");
            switch (locale) {
                case "en":
                    req.getSession().setAttribute("locale", "en");
                    break;
                case "ru":
                    req.getSession().setAttribute("locale", "ru");
                    break;
            }
        }
    }
}
