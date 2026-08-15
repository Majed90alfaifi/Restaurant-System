package org.majed.project252.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private final String url = getEnv(
            "DB_URL",
            "jdbc:mysql://localhost:3306/cpit252project_db"
    );

    private final String username = getEnv(
            "DB_USERNAME",
            "root"
    );

    private final String password = getEnv(
            "DB_PASSWORD",
            ""
    );

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {

        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }

        return instance;
    }

    private static String getEnv(String key, String defaultValue) {
        String value = System.getenv(key);

        if (value == null || value.isEmpty()) {
            return defaultValue;
        }

        return value;
    }
}
