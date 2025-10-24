package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static DBConnection instance;
    private final Connection connection;

    private DBConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/thodakadefx", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public DBConnection getInstance() {
        return null == instance ? new DBConnection() : instance;
    }
}
