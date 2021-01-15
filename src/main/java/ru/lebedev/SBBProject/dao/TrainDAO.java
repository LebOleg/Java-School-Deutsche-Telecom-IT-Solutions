package ru.lebedev.SBBProject.dao;

public interface TrainDAO {
    Integer getCurrentAvailableSeats(String trainNumber);
}
