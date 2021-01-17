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

import java.time.LocalDate;
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

    @Transactional
    public List<Ticket> findTicket(SearchTicketAttributes searchTicketAttributes) {
        LocalDateTime fromTime = CustomConverter.convertStringToTimeAndDate(searchTicketAttributes.getFromTime(),
                searchTicketAttributes.getDate());
        LocalDateTime toTime = CustomConverter.convertStringToTimeAndDate(searchTicketAttributes.getToTime(),
                searchTicketAttributes.getDate());

        return timetableDAO.getAvailableTicket(searchTicketAttributes, fromTime, toTime);
    }

    @Transactional
    public void buyTicket(String username, TicketDTO ticketDTO, PassengerDTO passengerDTO) {
        User user = userDAO.getUserByUsername(username).get();
        Ticket ticket = convertToTicket(ticketDTO);
        Passenger passenger = convertToPassenger(passengerDTO);
        Train train = ticket.getTrain();
        passenger.setUser(user);

        Optional<Passenger> dbPassenger = passengerDAO.getPassengerIfExists(passenger);

        if(!dbPassenger.isPresent()) {
            passengerDAO.save(passenger);
            ticket.setPassenger(passenger);
        } else {
            ticket.setPassenger(dbPassenger.get());
        }

        train.setAvailableSeats(train.getAvailableSeats() - 1);

        ticketDAO.save(ticket);
    }

    private Ticket convertToTicket(TicketDTO ticketDTO) {

        Station sourceStation = stationDAO.getStationByName(ticketDTO.getSourceStation()).get();
        Station destinationStation = stationDAO.getStationByName(ticketDTO.getDestinationStation()).get();
        Train train = trainDAO.getTrainByNumber(ticketDTO.getTrain()).get();
        LocalDateTime departureTime = CustomConverter.convertStringToTimeAndDate(ticketDTO.getDepartureTime(), ticketDTO.getDateTicket());
        LocalDateTime arrivalTime = CustomConverter.convertStringToTimeAndDate(ticketDTO.getArrivalTime(), ticketDTO.getDateTicket());

        Ticket ticket = Ticket.createTicket(sourceStation, destinationStation, departureTime, arrivalTime, train);

        return ticket;
    }

    private Passenger convertToPassenger(PassengerDTO passengerDTO) {
        String name = passengerDTO.getName();
        String lastName = passengerDTO.getLastName();
        String middleName = passengerDTO.getMiddleName();
        String email = passengerDTO.getEmail();
        LocalDate birth = CustomConverter.convertStringToDate(passengerDTO.getBirthday());
        String passport = passengerDTO.getPassportNumber();

        Passenger passenger = Passenger.createPassenger(name, lastName, middleName, birth, email, passport);

        return passenger;
    }
}

