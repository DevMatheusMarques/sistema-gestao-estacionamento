package org.compass.model.dao;

import org.compass.model.entities.Ticket;

import java.util.List;

public interface TicketDao {
    void insertTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    void deleteTicket(Ticket ticket);
    Ticket getTicketById(int id);
    List<Ticket> getTicketsAll();
}
