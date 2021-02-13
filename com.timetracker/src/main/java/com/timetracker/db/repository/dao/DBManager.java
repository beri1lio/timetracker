package com.timetracker.db.repository.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static String dataBaseURL = "jdbc:mysql://localhost:3306/timetracker?user=root&password=4815162342&useSSL=false&allowPublicKeyRetrieval=true";

    private static DBManager instance;

    private DBManager() {

    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseURL);
    }
}
