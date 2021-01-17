package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Train;

import java.util.Optional;

public interface TrainDAO {
    Integer getCurrentAvailableSeats(String trainNumber);

    Optional<Train> getTrainByNumber(String number);

    void updateAvailableSeats(Train train);
}
