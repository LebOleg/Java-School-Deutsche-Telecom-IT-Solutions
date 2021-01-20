package ru.lebedev.SBBProject.service;

import ru.lebedev.SBBProject.dto.SearchStationTimetableDTO;
import ru.lebedev.SBBProject.dto.StationTimetableDTO;
import ru.lebedev.SBBProject.model.Timetable;

import java.util.List;

public interface StationService {
    String getAutofilledStation(String partName);

    List<StationTimetableDTO> getStationTimetable(SearchStationTimetableDTO stationTimetable);
}
