package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Timetable;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;


public interface TimetableDAO {
    List<Tuple> getStationTimetable(String station, LocalDateTime startDay, LocalDateTime endDay);

    List<Tuple> getAvailableTicket(List<String> suitableRoutes, LocalDateTime fromTime, LocalDateTime toTime, String fromStation, String toStation);

    void saveInTimetable(Timetable timetable);
}