package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface TimetableDAO {
    List<Timetable> getStationTimetable(String station);

    List<Timetable> getFirstStationInPath(String station, LocalDateTime fromTime, LocalDateTime toTime);

    List<Timetable> getLastStationInPath(String station, LocalDateTime fromTime, Set<String> trainsNumber);
}