package org.compass.model.dao.implement;

import org.compass.db.DB;
import org.compass.model.dao.TicketDao;
import org.compass.model.entities.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class TicketDaoJDBC implements TicketDao {

    private final Connection connection;

    public TicketDaoJDBC() {
        this.connection = DB.getConnection();
    }

    @Override
    public Boolean registraTicket(Ticket ticket) {
        String sql = "INSERT INTO tickets (placa, tipo_veiculo, datetime_entrada, cancela_entrada, ocupacao_vagas, vaga_inicial) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, ticket.getPlaca());
            ps.setString(2, ticket.getTipoVeiculo());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, ticket.getCancelaEntrada());
            ps.setInt(5, ticket.getOcupacaoVagas());
            ps.setInt(6, ticket.getVagaInicial());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            System.out.println("Ticket registrado com sucesso!");
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registrarSaida(Ticket ticket, int numeroCancelaSaida) {
        String sql = "UPDATE tickets SET datetime_saida = ?, preco_final = ?, cancela_saida = ?, vaga_inicial = ? WHERE id = ?";

        Cobranca cobranca = new Cobranca(ticket);
        float valorFinal = cobranca.calcularValor();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setFloat(2, valorFinal);
            ps.setInt(3, numeroCancelaSaida);
            ps.setInt(4, -1);
            ps.setInt(5, ticket.getId());

            ps.executeUpdate();

            DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            System.out.println("Ticket:");
            System.out.println("ID: " + ticket.getId());
            System.out.println("Placa: " + ticket.getPlaca());
            System.out.println("Tipo Veiculo: " + ticket.getTipoVeiculo());
            System.out.println("Data e Hora de Entrada: " + ticket.getDataHoraEntrada().format(formatarData));
            System.out.println("Data e Hora de Saida: " + LocalDateTime.now().format(formatarData));
            System.out.println("Cancela de Entrada: " + ticket.getCancelaEntrada());
            System.out.println("Cancela de Saida: " + numeroCancelaSaida);
            System.out.println("Quantidade de vagas ocupadas: " + ticket.getOcupacaoVagas());
            System.out.println(String.format("Valor final Pago: R$ %.2f", valorFinal));
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar valor do ticket.", e);
        }
    }

    @Override
    public Map<String, Object> getTicketByPlaca(String placa) throws SQLException {
        Map<String, Object> resultado = new HashMap<>();

        String sql = "SELECT * FROM tickets WHERE placa = ? AND preco_final IS NULL";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, placa);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Ticket ticket = this.constroiTicket(resultSet);
            resultado.put("ticket", ticket);
            resultado.put("sucesso", true);
            resultado.put("mensagem", "Ticket encontrado.");
            return resultado;
        }
        resultado.put("ticket", null);
        resultado.put("sucesso", false);
        resultado.put("mensagem", "Ticket não encontrado.");

        return resultado;
    }

    @Override
    public Ticket constroiTicket(ResultSet resultSet) throws SQLException {
        // Cria um Ticket com a placa extraída do ResultSet
        Ticket ticket = new Ticket();

        // Define os atributos do Ticket com base no ResultSet
        ticket.setId(resultSet.getInt("id"));
        ticket.setDataHoraEntrada(resultSet.getTimestamp("datetime_entrada").toLocalDateTime());

        // Trata o caso onde datetime_saida pode ser nulo
        if (resultSet.getTimestamp("datetime_saida") != null) {
            ticket.setDataHoraSaida(resultSet.getTimestamp("datetime_saida").toLocalDateTime());
        } else {
            ticket.setDataHoraSaida(null); // Define como null ou um valor padrão se necessário
        }
        ticket.setPlaca(resultSet.getString("placa"));
        ticket.setCancelaEntrada(resultSet.getInt("cancela_entrada"));
        ticket.setCancelaSaida(resultSet.getInt("cancela_saida"));
        ticket.setPrecoFinal(resultSet.getDouble("preco_final"));
        ticket.setTipoVeiculo(resultSet.getString("tipo_veiculo"));
        ticket.setVagaInicial(resultSet.getInt("vaga_inicial"));
        ticket.setOcupacaoVagas(resultSet.getInt("ocupacao_vagas"));

        return ticket;
    }
}
