package ru.lebedev.SBBProject.service;

import ru.lebedev.SBBProject.dto.PassengerDTO;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.dto.TicketDTO;
import ru.lebedev.SBBProject.model.Ticket;

import java.util.List;


public interface TicketService {

    List<Ticket> findTicket(SearchTicketAttributes searchTicketAttributes);

    public void buyTicket(String username, TicketDTO ticketDTO, PassengerDTO passengerDTO);
}
