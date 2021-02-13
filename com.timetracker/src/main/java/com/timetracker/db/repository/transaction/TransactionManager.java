package com.timetracker.db.repository.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    public Object execute(TransactionOperation transactionOperation, Connection connection) throws SQLException {
        Object result = null;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            result = transactionOperation.operation(connection);
        } catch (SQLException throwables) {
            if(connection != null){
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return result;
    }
}
