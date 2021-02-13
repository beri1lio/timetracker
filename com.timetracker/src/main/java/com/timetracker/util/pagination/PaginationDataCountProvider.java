package com.timetracker.util.pagination;

import java.sql.SQLException;

public interface PaginationDataCountProvider {
    int provideDataCount(String search) throws SQLException;
}
