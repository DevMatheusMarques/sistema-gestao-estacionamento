package org.compass.model.dao;

import org.compass.model.entities.Ticket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface TicketDao {
    Boolean registraTicket(Ticket ticket);
    void registrarSaida(Ticket ticket, int numeroCancelaSaida) throws SQLException;
    Ticket constroiTicket(ResultSet resultSet) throws SQLException;
    Map<String, Object> getTicketByPlaca(String placa) throws SQLException;
}
