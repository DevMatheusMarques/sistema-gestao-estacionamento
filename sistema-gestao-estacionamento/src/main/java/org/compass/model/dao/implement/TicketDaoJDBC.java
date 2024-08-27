package org.compass.model.dao.implement;

import org.compass.model.dao.TicketDao;
import org.compass.model.entities.Ticket;

import java.sql.Connection;
import java.util.List;

public class TicketDaoJDBC implements TicketDao {

    private Connection connection;

    public TicketDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertTicket(Ticket ticket) {

    }

    @Override
    public void updateTicket(Ticket ticket) {

    }

    @Override
    public void deleteTicket(Ticket ticket) {

    }

    @Override
    public Ticket getTicketById(int id) {
        return null;
    }

    @Override
    public List<Ticket> getTicketsAll() {
        return List.of();
    }
}
