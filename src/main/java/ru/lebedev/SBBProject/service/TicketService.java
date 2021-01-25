package ru.lebedev.SBBProject.service;

import ru.lebedev.SBBProject.dto.PassengerDTO;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.dto.TicketDTO;
import ru.lebedev.SBBProject.model.Ticket;

import java.security.Principal;
import java.util.List;


public interface TicketService {

    List<TicketDTO> findTicket(SearchTicketAttributes searchTicketAttributes);

    void buyTicket(String username, PassengerDTO passengerDTO);

    List<Ticket> getUserTickets(Principal principal);
}
