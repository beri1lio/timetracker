package com.timetracker.db.repository.dao;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection Pool.
 */
public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

   private static ConnectionPool instance;

    /**
     * Private constructor in order not to be able to create a new.
     */
    private ConnectionPool() {

    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        Context ctx;
        Connection c = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetracker");
            c = ds.getConnection();
        }catch (NamingException | SQLException e){
            LOGGER.error(e.getMessage());
        }
        return c;
    }
}
