package org.compass.application;

import org.compass.db.DB;
import org.compass.db.DbException;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM carros;");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("modelo"));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
            DB.closeConnection();
        }

    }
}