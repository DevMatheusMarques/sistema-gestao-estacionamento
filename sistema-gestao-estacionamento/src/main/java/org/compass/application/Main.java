package org.compass.application;

import org.compass.db.DB;
import org.compass.db.DbException;
import org.compass.model.entities.Cobranca;

import java.sql.*;

import static org.compass.db.DB.getConnection;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM carro_passeios;");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("categoria"));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
            DB.closeConnection();
        }

        Cobranca cobranca = new Cobranca();

        System.out.println("Valor mensalidade: " + cobranca.getValorMensalidade());

        System.out.println();

    }
}