package com.timetracker.util.pagination;

import java.sql.SQLException;
import java.util.List;

public interface PaginationDataProvider {
    List provideData(int offset, int limit, String orderBy, String search) throws SQLException;
}
