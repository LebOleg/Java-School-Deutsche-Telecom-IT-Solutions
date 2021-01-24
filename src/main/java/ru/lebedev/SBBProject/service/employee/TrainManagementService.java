package ru.lebedev.SBBProject.service.employee;

import ru.lebedev.SBBProject.dto.TrainDTO;
import ru.lebedev.SBBProject.model.Train;

import java.util.List;

public interface TrainManagementService {

    Boolean createTrain(String trainInfo);

    List<TrainDTO> getAllTrains();
}
