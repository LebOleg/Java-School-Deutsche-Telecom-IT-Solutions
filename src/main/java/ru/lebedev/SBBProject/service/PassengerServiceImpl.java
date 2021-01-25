package ru.lebedev.SBBProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.lebedev.SBBProject.dao.PassengerDAO;
import ru.lebedev.SBBProject.model.Passenger;

import java.util.Optional;

@Controller
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerDAO passengerDAO;

    @Override
    public Optional<Passenger> getPassenger(Passenger passenger) {
        return passengerDAO.getPassengerIfExists(passenger);
    }

    @Override
    public Boolean passengerOnTrainExists(Passenger passenger) {
        Optional<Passenger> passengerFromDb = passengerDAO.getPassengerIfExists(passenger);

        if (passengerFromDb.isPresent()) {
            return passengerDAO.passengerOnTrainExists(passengerFromDb.get());
        }

        return false;
    }
}
