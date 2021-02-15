package com.timetracker.db.repository.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static String dataBaseURL = "jdbc:mysql://localhost:3306/timetracker?user=root&password=4815162342&useSSL=false&allowPublicKeyRetrieval=true";

    private static ConnectionPool instance;

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
            e.printStackTrace();
        }
        return c;
//        return DriverManager.getConnection(dataBaseURL);
    }
}
