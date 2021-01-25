package ru.lebedev.SBBProject.service;

import ru.lebedev.SBBProject.model.Passenger;

import java.util.Optional;

public interface PassengerService {

    Optional<Passenger> getPassenger(Passenger passenger);

    Boolean passengerOnTrainExists(Passenger passenger);
}
