package ru.lebedev.SBBProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.SBBProject.dao.StationDAO;
import ru.lebedev.SBBProject.dao.TimetableDAO;
import ru.lebedev.SBBProject.model.Timetable;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    private StationDAO stationDAO;
    @Autowired
    private TimetableDAO timetableDAO;

    @Override
    public String getAutofilledStation(String partName) {
        int eqPos = partName.indexOf("=");
        partName = partName.substring(eqPos + 1);
        return stationDAO.getAutofilledStation(partName);
    }

    @Override
    public List<Timetable> getStationTimetable(String station) {
        return timetableDAO.getStationTimetable(station);
    }
}
