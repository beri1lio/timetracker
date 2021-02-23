package com.timetracker.servlet.user;

import com.timetracker.db.entity.UserSortBy;
import com.timetracker.service.UserService;
import com.timetracker.service.impl.UserServiceImpl;
import com.timetracker.util.pagination.PaginationDataCountProvider;
import com.timetracker.util.pagination.PaginationDataDefaultOrderProvider;
import com.timetracker.util.pagination.PaginationDataProvider;
import com.timetracker.util.pagination.PaginationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Pagination users.
 */
@WebServlet("/users")
public class UserAdminServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    private final PaginationDataProvider dataProvider = new PaginationDataProvider() {
        @Override
        public List provideData(int offset, int limit, String orderBy, String search) throws SQLException {

            return userService.findAllUsers(offset, 10, orderBy, search);
        }
    };

    private final PaginationDataCountProvider dataCountProvider = new PaginationDataCountProvider() {
        @Override
        public int provideDataCount(String search) throws SQLException {
            return userService.getUserCount(search);
        }
    };

    private final PaginationDataDefaultOrderProvider dataDefaultOrderProvider = new PaginationDataDefaultOrderProvider() {
        @Override
        public String provideDataOrder() {
            return UserSortBy.NAME.name();
        }
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaginationUtil.executePagination(req, resp, dataProvider, dataCountProvider, dataDefaultOrderProvider);

        req.getRequestDispatcher("user-admin.jsp").forward(req, resp);
    }
}
