package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {

    public static Connection getConnection() {

        try {
            Class.forName(ApplicationConfig.get("db.driver"));

            return DriverManager.getConnection(
                    ApplicationConfig.get("db.url"),
                    ApplicationConfig.get("db.username"),
                    ApplicationConfig.get("db.password")
            );

        } catch (Exception e) {
            throw new RuntimeException("DB Connection Failed", e);
        }
    }
}
