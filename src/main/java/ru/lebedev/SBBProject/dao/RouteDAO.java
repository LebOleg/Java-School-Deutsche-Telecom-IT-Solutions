package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteDAO {

    void save(Route route);

    Optional<Route> getRoute(String routeNumber, String toStation);

    List<String> getSuitableRoutes(String fromStation, String toStation);

    List<Route> getRouteByNumber(String number);
}
