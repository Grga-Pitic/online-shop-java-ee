package db;

import java.sql.*;

public class DBConnection {

    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";


    private static DBConnection instance;

    private Connection connection;
    private Statement statement;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = connection.createStatement();
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }

        return instance;
    }

    public Statement getStatement() {
        return statement;
    }

    public PreparedStatement createPrepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }

    public ResultSet executeQuery(String query) throws SQLException {

        return statement.executeQuery(query);

    }
}
