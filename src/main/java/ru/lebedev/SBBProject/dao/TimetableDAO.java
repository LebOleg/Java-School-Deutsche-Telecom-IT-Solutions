package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Timetable;

import java.util.List;

public interface TimetableDAO {
    List<Timetable> getStationTimetable(String station);
}
