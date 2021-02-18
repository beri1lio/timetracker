package com.timetracker.db.repository.transaction;

import com.timetracker.servlet.AuthorizationServlet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private static final Logger LOGGER = Logger.getLogger(TransactionManager.class);

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
            LOGGER.error(throwables.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return result;
    }
}
