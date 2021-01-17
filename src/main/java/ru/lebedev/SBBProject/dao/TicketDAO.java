package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Ticket;
import ru.lebedev.SBBProject.model.Timetable;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TicketDAO {

    void save(Ticket ticket);
}
