package com.timetracker.servlet.task;

import com.timetracker.service.TaskService;
import com.timetracker.service.UserService;
import com.timetracker.servlet.user.DeleteUserServlet;
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

public class DeleteTaskServletTest {
    private static final String TEST_TASK_ID_PARAM = "1";
    private static final int TEST_TASK_ID = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    public TaskService taskServiceMock;

    @InjectMocks
    public DeleteTaskServlet servlet = new DeleteTaskServlet();

    @Mock
    public HttpServletRequest requestMock;

    @Mock
    public HttpServletResponse responseMock;

    @Test
    public void taskShouldBeDeleted() throws ServletException, IOException, SQLException {
        when(requestMock.getParameter("task-id")).thenReturn(TEST_TASK_ID_PARAM);
        servlet.doPost(requestMock, responseMock);

        verify(taskServiceMock).deleteTask(TEST_TASK_ID);
        verify(responseMock).sendRedirect("/tasks");
    }
}