package ru.lebedev.SBBProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.SBBProject.dao.TrainDAO;

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainDAO trainDAO;

    @Override
    public Integer getAvailableSeats(String trainNumber) {
        if (trainNumber.contains("=")) {
            trainNumber = trainNumber.substring(trainNumber.indexOf("=") + 1);
        }
        return trainDAO.getCurrentAvailableSeats(trainNumber);
    }

}
