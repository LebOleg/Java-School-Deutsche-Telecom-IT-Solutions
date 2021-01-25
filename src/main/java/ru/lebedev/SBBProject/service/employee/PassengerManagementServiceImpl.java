package ru.lebedev.SBBProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.SBBProject.dao.PassengerDAO;
import ru.lebedev.SBBProject.model.Passenger;

import java.util.List;

@Service
public class PassengerManagementServiceImpl implements PassengerManagementService {

    @Autowired
    private PassengerDAO passengerDAO;

    @Override
    public List<Passenger> getPassengersOnTrain(String trainNumber) {
        return passengerDAO.getPassengersOnTrain(Integer.parseInt(trainNumber));
    }
}
