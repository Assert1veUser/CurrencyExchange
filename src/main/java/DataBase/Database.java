package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private static final String JDBC_URL = "jdbc:sqlite:src\\main\\resources\\users.db";
    private static final String SCHEMA_SQL = """
			CREATE TABLE IF NOT EXISTS currency_exchange (
			    id INTEGER PRIMARY KEY AUTOINCREMENT,
			    "value" REAL NOT NULL,
			    nominal INTEGER NOT NULL,
			    currency_name VARCHAR(100) NOT NULL,
			    currency_code VARCHAR(3) NOT NULL,
			    "date" DATE NOT NULL
			);""";
    private Connection connection;

    public Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(JDBC_URL);
                connection.setAutoCommit(true);
                initDb();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
    private void initDb() {
        try (PreparedStatement statement = connection.prepareStatement(SCHEMA_SQL)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }
            connection = null;
            return true;
        }
        return false;
    }
}