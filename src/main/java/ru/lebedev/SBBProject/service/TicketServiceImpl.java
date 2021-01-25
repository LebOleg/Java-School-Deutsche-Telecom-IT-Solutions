package ru.lebedev.SBBProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.*;
import ru.lebedev.SBBProject.dto.PassengerDTO;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.dto.TicketDTO;
import ru.lebedev.SBBProject.model.*;
import ru.lebedev.SBBProject.utility.CustomConverter;

import javax.persistence.Tuple;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TimetableDAO timetableDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private StationDAO stationDAO;
    @Autowired
    private TrainDAO trainDAO;
    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private PassengerDAO passengerDAO;
    @Autowired
    private RouteDAO routeDAO;

    @Transactional
    public List<TicketDTO> findTicket(SearchTicketAttributes searchTicketAttributes) {
        LocalDateTime fromTime = CustomConverter.convertStringToTimeAndDate(searchTicketAttributes.getFromTime(),
                searchTicketAttributes.getDate());
        LocalDateTime toTime = CustomConverter.convertStringToTimeAndDate(searchTicketAttributes.getToTime(),
                searchTicketAttributes.getDate());
        String fromStation = searchTicketAttributes.getFromStation();
        String toStation = searchTicketAttributes.getToStation();
        List<TicketDTO> tickets = new ArrayList<>();

        if (fromStation.equals(toStation) || fromTime == null || toTime == null) {
            return tickets;
        }


        List<String> suitableRoutes = routeDAO.getSuitableRoutes(fromStation, toStation);

        List<Tuple> ticketsTuple = timetableDAO.getAvailableTicket(suitableRoutes, fromTime, toTime, fromStation, toStation);


        for (int i = 0; i < ticketsTuple.size(); i = i + 2) {
            Tuple fromStationTimetable = ticketsTuple.get(i);
            Tuple toStationTimetable = ticketsTuple.get(i + 1);

            LocalDateTime departureTime = fromStationTimetable.get("time", Timestamp.class).toLocalDateTime();
            LocalDateTime arrivalTime = toStationTimetable.get("time", Timestamp.class).toLocalDateTime();
            Integer trainId = fromStationTimetable.get("trainNumber", Integer.class);
            String routeNumber = fromStationTimetable.get("routeNumber", String.class);

            Train train = trainDAO.getTrainById(trainId);

            TicketDTO ticket = new TicketDTO(fromStation, toStation, trainId.toString(), routeNumber, departureTime, arrivalTime, train.getAvailableSeats());
            tickets.add(ticket);


        }

        return tickets;
    }

    @Transactional
    public void buyTicket(String username, PassengerDTO passengerDTO) {
        User user = userDAO.getUserByUsername(username).get();
        Ticket ticket = convertToTicket(passengerDTO.getTicketDTO());
        Passenger passenger = PassengerDTO.convertToPassenger(passengerDTO);
        Train train = ticket.getTrain();
        passenger.setUser(user);

        ticket.setPassenger(passenger);
        train.setAvailableSeats(train.getAvailableSeats() - 1);
        ticketDAO.save(ticket);
    }

    private Ticket convertToTicket(TicketDTO ticketDTO) {

        Station sourceStation = stationDAO.getStationByName(ticketDTO.getSourceStation()).get();
        Station destinationStation = stationDAO.getStationByName(ticketDTO.getDestinationStation()).get();
        Train train = trainDAO.getTrainById(Integer.parseInt(ticketDTO.getTrain()));
        LocalDateTime departureTime = CustomConverter.convertStringToTimeAndDate(ticketDTO.getDepartureTime(), ticketDTO.getDateTicket());
        LocalDateTime arrivalTime = CustomConverter.convertStringToTimeAndDate(ticketDTO.getArrivalTime(), ticketDTO.getDateTicket());

        Ticket ticket = Ticket.createTicket(sourceStation, destinationStation, departureTime, arrivalTime, train);

        return ticket;
    }

    @Override
    public List<Ticket> getUserTickets(Principal principal) {
        String username = principal.getName();
        List<String> passengers = passengerDAO.getPassengerId(username);
        List<Ticket> tickets = ticketDAO.getUsersTicket(passengers);

        return tickets;
    }
}

