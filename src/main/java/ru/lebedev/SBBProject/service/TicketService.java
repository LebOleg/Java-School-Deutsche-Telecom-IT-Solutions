package ru.lebedev.SBBProject.service;

import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.model.Timetable;

import java.time.LocalDateTime;
import java.util.Map;

public interface TicketService {

    Map<Timetable, LocalDateTime> findTicket(SearchTicketAttributes searchTicketAttributes);
}
