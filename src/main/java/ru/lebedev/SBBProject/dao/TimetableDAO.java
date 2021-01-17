package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.model.Ticket;
import ru.lebedev.SBBProject.model.Timetable;

import java.time.LocalDateTime;
import java.util.List;


public interface TimetableDAO {
    List<Timetable> getStationTimetable(String station);

    List<Ticket> getAvailableTicket(SearchTicketAttributes ticketAttributes, LocalDateTime fromTime, LocalDateTime toTime);
}