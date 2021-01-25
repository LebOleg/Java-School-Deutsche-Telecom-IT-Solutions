package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerDAO {

    Optional<Passenger> getPassengerIfExists(Passenger p);

    void save(Passenger p);

    List<String> getPassengerId(String username);

    List<Passenger> getPassengersOnTrain(Integer trainNumber);

    Boolean passengerOnTrainExists(Passenger p);
}
