package com.timetracker.db.repository.transaction;

import java.sql.Connection;

/**
 * Transaction operation.
 */
public interface TransactionOperation {

    Object operation(Connection connection);
}
