package ru.lebedev.SBBProject.service.employee;

import ru.lebedev.SBBProject.model.RouteNumber;

import java.util.List;

public interface RouteManagementService {

    Boolean createPathInRoute(String path);

    List<RouteNumber> getAllRouteNumbers();
}
