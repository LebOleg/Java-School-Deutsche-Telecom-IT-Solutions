package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Ticket;

import java.util.List;

public interface TicketDAO {

    void save(Ticket ticket);

    List<Ticket> getUsersTicket(List<String> passengers);
}
