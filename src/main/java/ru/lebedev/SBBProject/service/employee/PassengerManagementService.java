package ru.lebedev.SBBProject.service.employee;

import ru.lebedev.SBBProject.model.Passenger;

import java.util.List;

public interface PassengerManagementService {

    List<Passenger> getPassengersOnTrain(String trainNumber);
}
