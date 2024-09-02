package org.compass.model.dao.implement;

import org.compass.db.DB;
import org.compass.db.DbException;
import org.compass.model.dao.VeiculoDao;
import org.compass.model.entities.*;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VeiculoDaoJDBC implements VeiculoDao {

    private final Connection connection;

    public VeiculoDaoJDBC() throws SQLException {
        this.connection = DB.getConnection();
    }

    public Boolean registraVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO veiculos (placa, tipo, mensalista, ocupacao_vagas, vaga_inicial) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, veiculo.getPlaca());
            ps.setString(2, String.valueOf(veiculo.getTipo()));
            ps.setBoolean(3, veiculo.getMensalista());
            ps.setInt(4, veiculo.getOcupacaoVagas());
            ps.setInt(5, veiculo.getVagaInicial());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            System.out.println("Veículo registrado com sucesso!");
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getByVagaInicial(String placa) {
        String sql = "SELECT vaga_inicial FROM veiculos WHERE placa = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, placa);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("vaga_inicial");
                } else {
                    throw new SQLException("Vaga Inicial não encontrada.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTipoByPlaca(String placa) throws SQLException {
        String sql = "SELECT tipo FROM veiculos WHERE placa = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, placa);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("tipo");
                } else {
                    throw new SQLException("Tipo não encontrado.");
                }
            }
        }
    }

    public Map<String, Object> getVeiculoById(Integer id) throws SQLException {
        Map<String, Object> resultado = new HashMap<>();

        String sql = "SELECT * FROM veiculos WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Veiculo veiculo = new Veiculo();
            veiculo.setId(resultSet.getInt("id"));
            resultado.put("veiculo", veiculo);
            resultado.put("sucesso", true);
            resultado.put("mensagem", "Veículo encontrado.");
            return resultado;
        }
        resultado.put("veiculo", null);
        resultado.put("sucesso", false);
        resultado.put("mensagem", "Veículo não encontrado.");

        return resultado;
    }

    public Map<String, Object> getVeiculoByPlaca(String placa) throws SQLException {
        Map<String, Object> resultado = new HashMap<>();

        String sql = "SELECT * FROM veiculos WHERE placa = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, placa);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Veiculo veiculo = new Veiculo();  // Suponha que você cria o objeto Veiculo aqui
            veiculo.setId(resultSet.getInt("id"));
            resultado.put("veiculo", veiculo);
            resultado.put("sucesso", true);
            resultado.put("mensagem", "Veículo encontrado.");
            return resultado;
        }
        resultado.put("veiculo", null);
        resultado.put("sucesso", false);
        resultado.put("mensagem", "Veículo não encontrado.");

        return resultado;
    }
}
