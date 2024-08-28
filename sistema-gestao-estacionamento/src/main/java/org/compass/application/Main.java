package org.compass.application;

import org.compass.db.DB;
import org.compass.db.DbException;
import org.compass.model.entities.*;

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

        Cancela cancela = new Cancela();

        VeiculoServicoPublico publico = new VeiculoServicoPublico(1, "12d323");

        System.out.println("Informe os dados: " + cancela.permiteEntrar(publico, "1"));

        System.out.println();

        Moto publico2 = new Moto(1, "12d323", Categoria.MENSAL);

        System.out.print("Informe os dados: " + cancela.permiteSair(publico2, "10"));
    }
}