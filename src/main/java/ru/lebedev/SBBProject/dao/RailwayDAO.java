package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Railway;

import java.util.List;
import java.util.Optional;

public interface RailwayDAO {

    Optional<Railway> getRailway(String fromStation, String toStation);

    void save(Railway railway);

    List<Railway> getAllRailways();
}
