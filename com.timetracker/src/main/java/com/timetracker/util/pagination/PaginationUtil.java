package com.timetracker.util.pagination;

import com.timetracker.servlet.AuthorizationServlet;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PaginationUtil {

    private static final Logger LOGGER = Logger.getLogger(PaginationUtil.class);

    private PaginationUtil(){}

    public static void executePagination(HttpServletRequest req, HttpServletResponse resp,
                                         PaginationDataProvider dataProvider,
                                         PaginationDataCountProvider dataCountProvider,
                                         PaginationDataDefaultOrderProvider dataDefaultOrderProvider) {

        String search = getSearchParam(req, "");
        int currentPage = getCurrentPageNumber(req, 0);
        int maxPageCount = getMaxPageNumber(search, dataCountProvider, 0);
        int offset = getOffset(currentPage);
        String orderBy = getOrderBy(req, dataDefaultOrderProvider.provideDataOrder());
        List data = getData(offset, 10, orderBy, search, dataProvider, Collections.EMPTY_LIST);

        req.setAttribute("data", data);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("maxPage", maxPageCount);
        req.setAttribute("orderBy", orderBy);
        req.setAttribute("search", search);
    }

    private static int getCurrentPageNumber(HttpServletRequest request, int defaultCurrentPageNumber) {
        String currentPageNumberParam = request.getParameter("currentPage");
        if (currentPageNumberParam != null) {
            return Integer.parseInt(currentPageNumberParam);
        }
        return defaultCurrentPageNumber;
    }

    private static int getOffset(int currentPageNumber) {
        return currentPageNumber * 10;
    }

    private static int getMaxPageNumber(String search, PaginationDataCountProvider dataCountProvider, int defaultMaxPageNumber) {
        try {
            return (int) Math.ceil(dataCountProvider.provideDataCount(search) / 10.0);
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
            return defaultMaxPageNumber;
        }
    }

    private static String getSearchParam(HttpServletRequest request, String defaultSearchParam) {
        String searchParam = request.getParameter("search");
        if (searchParam != null) {
            return searchParam;
        }
        return defaultSearchParam;
    }

    private static String getOrderBy(HttpServletRequest request, String defaultOrderBy) {
        String orderByParam = request.getParameter("orderBy");
        if (orderByParam != null) {
            return orderByParam;
        }
        return defaultOrderBy;
    }

    private static List getData(int offset, int limit, String orderBy, String searchParam, PaginationDataProvider dataProvider, List defaultData) {
        try {
            return dataProvider.provideData(offset, 10, orderBy, searchParam);
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
            return defaultData;
        }
    }

}
