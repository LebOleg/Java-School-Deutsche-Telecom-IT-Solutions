package ru.lebedev.SBBProject.service;

import ru.lebedev.SBBProject.model.Timetable;

import java.util.List;

public interface StationService {
    String getAutofilledStation(String partName);

    List<Timetable> getStationTimetable(String station);
}
