package com.timetracker.servlet.user;

import com.timetracker.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteUserServletTest {
    private static final String TEST_USER_ID_PARAM = "1";
    private static final int TEST_USER_ID = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    public UserService userServiceMock;

    @InjectMocks
    public DeleteUserServlet servlet = new DeleteUserServlet();

    @Mock
    public HttpServletRequest requestMock;

    @Mock
    public HttpServletResponse responseMock;

    @Test
    public void userShouldBeDeleted() throws ServletException, IOException, SQLException {
        when(requestMock.getParameter("user-id")).thenReturn(TEST_USER_ID_PARAM);
        servlet.doPost(requestMock, responseMock);

        verify(userServiceMock).deleteUser(TEST_USER_ID);
        verify(responseMock).sendRedirect("/users");
    }
}