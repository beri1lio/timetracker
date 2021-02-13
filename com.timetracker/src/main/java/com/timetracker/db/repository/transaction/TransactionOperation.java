package com.timetracker.db.repository.transaction;

import java.sql.Connection;

public interface TransactionOperation {

    Object operation(Connection connection);
}
