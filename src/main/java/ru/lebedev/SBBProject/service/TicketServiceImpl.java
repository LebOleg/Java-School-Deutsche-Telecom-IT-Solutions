package ru.lebedev.SBBProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.SBBProject.dao.TicketDAO;
import ru.lebedev.SBBProject.dao.TimetableDAO;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.model.Timetable;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private TimetableDAO timetableDAO;

    @Override
    public Map<Timetable, LocalDateTime> findTicket(SearchTicketAttributes searchTicketAttributes) {
        LocalDateTime fromDate = convertStringsToDate(searchTicketAttributes.getFromTime(), searchTicketAttributes.getDate());
        LocalDateTime toDate = convertStringsToDate(searchTicketAttributes.getToTime(), searchTicketAttributes.getDate());

        List<Timetable> supposedTrainsTimetablesFirstStation = timetableDAO.getFirstStationInPath(searchTicketAttributes.getFromStation(), fromDate, toDate);

        Set<String> supposedTrainNumber = getTrainNum(supposedTrainsTimetablesFirstStation);

        List<Timetable> supposedTrainsTimetableLastStation = timetableDAO.getLastStationInPath(searchTicketAttributes.getToStation(), fromDate, supposedTrainNumber);

        Map<Timetable, LocalDateTime> actualTrains = new HashMap<>();
        for(Timetable tLast: supposedTrainsTimetableLastStation ) {
            for (Timetable tFirst: supposedTrainsTimetablesFirstStation) {
                String firstTrainNumber = tFirst.getTrain().getNumber();
                String lastTrainNumber = tLast.getTrain().getNumber();
                if(lastTrainNumber.equals(firstTrainNumber) && tFirst.getDepartureTime().isBefore(tLast.getArrivalTime())) {
                    actualTrains.put(tFirst, tLast.getArrivalTime());
                    break;
                }
            }
        }

        return actualTrains;
    }

    private LocalDateTime convertStringsToDate(String time, String date) {
        String fullDate = date + "T" + time;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        return LocalDateTime.parse(fullDate, formatter);
    }

    private Set<String> getTrainNum(List<Timetable> timetables) {
        Set<String> trainNumbers = new HashSet<>();
        for(Timetable t: timetables) {
            trainNumbers.add(t.getTrain().getNumber());
        }
        return trainNumbers;
    }
}

