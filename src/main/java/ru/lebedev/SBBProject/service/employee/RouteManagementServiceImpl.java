package ru.lebedev.SBBProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.RailwayDAO;
import ru.lebedev.SBBProject.dao.RouteDAO;
import ru.lebedev.SBBProject.dao.RouteNumberDAO;
import ru.lebedev.SBBProject.dao.StationDAO;
import ru.lebedev.SBBProject.model.Railway;
import ru.lebedev.SBBProject.model.Route;
import ru.lebedev.SBBProject.model.RouteNumber;
import ru.lebedev.SBBProject.model.Station;

import java.util.List;
import java.util.Optional;

@Service
public class RouteManagementServiceImpl implements RouteManagementService {
    @Autowired
    private RouteNumberDAO routeNumberDAO;
    @Autowired
    private StationDAO stationDAO;
    @Autowired
    private RouteDAO routeDAO;
    @Autowired
    private RailwayDAO railwayDAO;

    @Transactional
    @Override
    public Boolean createPathInRoute(String path) {

        String[] pathAttributes = path.split("&");

        String routeNumber = pathAttributes[0].substring(pathAttributes[0].indexOf("=") + 1);
        String fromStation = pathAttributes[1].substring(pathAttributes[1].indexOf("=") + 1);
        String toStation = pathAttributes[2].substring(pathAttributes[2].indexOf("=") + 1);
        String travelTime = pathAttributes[3].substring(pathAttributes[3].indexOf("=") + 1).replace("%3A", ":");

        Optional<Railway> railway = railwayDAO.getRailway(fromStation, toStation);
        if (!railway.isPresent()) {
            return false;
        }

        Optional<RouteNumber> routeNumberFromDB = routeNumberDAO.getRouteNumber(routeNumber);

        if (routeNumberFromDB.isPresent()) {
            return savePath(routeNumberFromDB.get(), toStation, travelTime);

        } else {
            saveFirstPath(routeNumber, fromStation, toStation, travelTime);
            return true;
        }

    }

    private void saveFirstPath(String number, String fromStation, String toStation, String travelTime) {
        RouteNumber routeNumber = new RouteNumber(number);

        Station firstStation = stationDAO.getStationByName(fromStation).get();
        Station secondStation = stationDAO.getStationByName(toStation).get();

        Route firsStationRoute = new Route(routeNumber, firstStation, "00:00");
        Route secondStationRoute = new Route(routeNumber, secondStation, travelTime);

        routeDAO.save(firsStationRoute);
        routeDAO.save(secondStationRoute);
    }

    private boolean savePath(RouteNumber routeNumber, String toStation, String travelTime) {

        Optional<Route> route = routeDAO.getRoute(routeNumber.getNumber(), toStation);

        if (route.isPresent()) {
            return false;
        }
        Optional<RouteNumber> routeNumberFromDB = routeNumberDAO.getRouteNumber(routeNumber.getNumber());

        Station station = stationDAO.getStationByName(toStation).get();
        Route routeToSave = new Route(routeNumberFromDB.get(), station, travelTime);
        routeDAO.save(routeToSave);
        return true;
    }

    @Override
    public List<RouteNumber> getAllRouteNumbers() {
        return routeNumberDAO.getAllRouteNumber();
    }

    @Override
    public List<Route> getRouteByNumber(String number) {
        return routeDAO.getRouteByNumber(number);
    }
}