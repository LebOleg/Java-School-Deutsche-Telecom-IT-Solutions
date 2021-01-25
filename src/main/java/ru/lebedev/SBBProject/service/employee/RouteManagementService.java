package ru.lebedev.SBBProject.service.employee;

import ru.lebedev.SBBProject.model.Route;
import ru.lebedev.SBBProject.model.RouteNumber;

import java.util.List;

public interface RouteManagementService {

    Boolean createPathInRoute(String path);

    List<RouteNumber> getAllRouteNumbers();

    List<Route> getRouteByNumber(String number);
}
