package ru.lebedev.SBBProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.StationDAO;
import ru.lebedev.SBBProject.dao.TimetableDAO;
import ru.lebedev.SBBProject.dto.SearchStationTimetableDTO;
import ru.lebedev.SBBProject.dto.StationTimetableDTO;
import ru.lebedev.SBBProject.model.Station;
import ru.lebedev.SBBProject.model.Timetable;
import ru.lebedev.SBBProject.utility.CustomConverter;

import javax.persistence.Tuple;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<StationTimetableDTO> getStationTimetable(SearchStationTimetableDTO stationTimetable) {
        LocalDateTime startDay = CustomConverter.convertStringToTimeAndDate("00:00", stationTimetable.getDate())
                .minus(1, ChronoUnit.MINUTES);
        LocalDateTime endDay = startDay.plus(1, ChronoUnit.DAYS).plus(1, ChronoUnit.MINUTES);

        List<Tuple> timetableTuple = timetableDAO.getStationTimetable(stationTimetable.getStationName(), startDay, endDay);
        List<StationTimetableDTO> timetable = new ArrayList<>();

        for(Tuple s: timetableTuple) {
            StationTimetableDTO stationTimetableDTO = new StationTimetableDTO();

            LocalDateTime departure = s.get("timetableDate", Timestamp.class).toLocalDateTime();

            stationTimetableDTO.setDepartureTime(departure);
            stationTimetableDTO.setRouteNumber(s.get("routeNumber", String.class));

            timetable.add(stationTimetableDTO);
        }


        return timetable;
    }
}
