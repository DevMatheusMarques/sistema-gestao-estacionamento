package org.compass.model.dao.implement;

import org.compass.db.DB;
import org.compass.model.dao.VeiculoDao;
import org.compass.model.entities.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class VeiculoDaoJDBC implements VeiculoDao {

    // Conexão com o banco de dados
    private final Connection connection;

    // Construtor que inicializa a conexão usando a classe DB
    public VeiculoDaoJDBC() {
        this.connection = DB.getConnection();
    }

    @Override
    public Boolean registraVeiculo(Veiculo veiculo) {
        // SQL para inserir um novo veículo na tabela 'veiculos'
        String sql = "INSERT INTO veiculos (placa, tipo, mensalista, ocupacao_vagas, vaga_inicial) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Configura os parâmetros da consulta SQL
            ps.setString(1, veiculo.getPlaca());
            ps.setString(2, String.valueOf(veiculo.getTipo()));
            ps.setBoolean(3, veiculo.getMensalista());
            ps.setInt(4, veiculo.getOcupacaoVagas());
            ps.setInt(5, veiculo.getVagaInicial());

            // Executa a consulta de inserção
            ps.executeUpdate();

            // Recupera as chaves geradas automaticamente
            ResultSet rs = ps.getGeneratedKeys();
            System.out.println("Veículo registrado com sucesso!");

            // Retorna true se a inserção gerou uma chave, indicando que o veículo foi registrado
            return rs.next();

        } catch (SQLException e) {
            // Lança uma exceção em caso de erro na inserção
            throw new RuntimeException(e);
        }
    }

    public void atualizaVeiculo(String placa, int vagaInicial) throws SQLException {
        String sql = "UPDATE veiculos SET vaga_inicial = ? WHERE placa = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, vagaInicial);
            ps.setString(2, placa);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo.", e);
        }
    }

    public void registrarSaida(int veiculId) {
        String sql = "UPDATE veiculos SET vaga_inicial = -1 WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, veiculId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo.", e);
        }
    }

    @Override
    public int getByVagaInicial(String placa) {
        // SQL para obter a vaga inicial do veículo com a placa especificada
        String sql = "SELECT vaga_inicial FROM veiculos WHERE placa = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Configura o parâmetro da consulta SQL
            preparedStatement.setString(1, placa);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Retorna a vaga inicial se o veículo for encontrado
                if (resultSet.next()) {
                    return resultSet.getInt("vaga_inicial");
                } else {
                    // Lança uma exceção se a vaga inicial não for encontrada
                    throw new SQLException("Vaga Inicial não encontrada.");
                }
            }
        } catch (SQLException e) {
            // Lança uma exceção em caso de erro na consulta
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTipoByPlaca(String placa) throws SQLException {
        // SQL para obter o tipo do veículo com a placa especificada
        String sql = "SELECT tipo FROM veiculos WHERE placa = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Configura o parâmetro da consulta SQL
            preparedStatement.setString(1, placa);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Retorna o tipo do veículo se encontrado
                if (resultSet.next()) {
                    return resultSet.getString("tipo");
                } else {
                    // Lança uma exceção se o tipo não for encontrado
                    throw new SQLException("Tipo não encontrado.");
                }
            }
        }
    }

    @Override
    public Map<String, Object> getVeiculoByPlaca(String placa) throws SQLException {
        Map<String, Object> resultado = new HashMap<>();

        // SQL para obter todos os dados do veículo com a placa especificada
        String sql = "SELECT * FROM veiculos WHERE placa = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, placa);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Se o veículo for encontrado, popula o mapa de resultado
        if (resultSet.next()) {
            Veiculo veiculo = new Veiculo();
            veiculo.setId(resultSet.getInt("id"));
            resultado.put("veiculo", veiculo);
            resultado.put("id", veiculo.getId());
            resultado.put("sucesso", true);
            resultado.put("mensagem", "Veículo encontrado.");
            return resultado;
        }

        // Se o veículo não for encontrado, atualiza o mapa de resultado com valores nulos e mensagem de erro
        resultado.put("veiculo", null);
        resultado.put("sucesso", false);
        resultado.put("mensagem", "Veículo não encontrado.");

        return resultado;
    }
}
