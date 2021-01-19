package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.RouteNumber;

import java.util.List;
import java.util.Optional;

public interface RouteNumberDAO {

    Optional<RouteNumber> getRouteNumber(String number);

    void saveRouteNumber(RouteNumber routeNumber);

    List<RouteNumber> getAllRouteNumber();
}
