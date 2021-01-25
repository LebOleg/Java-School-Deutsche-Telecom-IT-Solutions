package ru.lebedev.SBBProject.service.employee;

import ru.lebedev.SBBProject.dto.TrainDTO;

import java.util.List;

public interface TrainManagementService {

    Boolean createTrain(String trainInfo);

    List<TrainDTO> getAllTrains();
}
