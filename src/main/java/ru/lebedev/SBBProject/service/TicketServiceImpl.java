package ru.lebedev.SBBProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.SBBProject.dao.TicketDAO;
import ru.lebedev.SBBProject.dao.TimetableDAO;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.model.Ticket;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TimetableDAO timetableDAO;


    public List<Ticket> findTicket(SearchTicketAttributes searchTicketAttributes) {

        return timetableDAO.getAvailableTicket(searchTicketAttributes);
    }
}

