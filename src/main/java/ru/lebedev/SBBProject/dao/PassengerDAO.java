package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Passenger;

import java.time.LocalDate;
import java.util.Optional;

public interface PassengerDAO {

    Optional<Passenger> getPassengerIfExists(Passenger p);
    void save(Passenger p);
}
