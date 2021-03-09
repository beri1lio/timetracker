package com.timetracker.servlet.category;

import com.timetracker.service.CategoryService;
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

public class DeleteCategoryServletTest {
    private static final String TEST_CATEGORY_ID_PARAM = "1";
    private static final int TEST_CATEGORY_ID = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    public CategoryService categoryServiceMock;

    @InjectMocks
    public DeleteCategoryServlet servlet = new DeleteCategoryServlet();

    @Mock
    public HttpServletRequest requestMock;

    @Mock
    public HttpServletResponse responseMock;

    @Test
    public void categoryShouldBeDeleted() throws ServletException, IOException, SQLException {
        when(requestMock.getParameter("category-id")).thenReturn(TEST_CATEGORY_ID_PARAM);
        servlet.doPost(requestMock, responseMock);

        verify(categoryServiceMock).deleteCategory(TEST_CATEGORY_ID);
        verify(responseMock).sendRedirect("/categories");
    }
}