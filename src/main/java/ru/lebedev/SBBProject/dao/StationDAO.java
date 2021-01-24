package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Station;

import java.util.List;
import java.util.Optional;

public interface StationDAO {
    String getAutofilledStation(String partName);

    Optional<Station> getStationByName(String name);

    void save(Station station);

    List<Station> getAllStations();
}
