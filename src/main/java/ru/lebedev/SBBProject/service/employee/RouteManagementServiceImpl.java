package ru.lebedev.SBBProject.service.employee;

import org.json.JSONObject;
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
    public String createPathInRoute(String path) {
        JSONObject jsonPath = new JSONObject(path);

        String routeNumber = jsonPath.getString("routeName");
        String fromStation = jsonPath.getString("routFromStation");
        String toStation = jsonPath.getString("routToStation");
        String travelTime = jsonPath.getString("travelTime");
        JSONObject result = new JSONObject();

        if(isFieldsEmpty(routeNumber, fromStation, toStation, travelTime)) {
            result.put("message", "Поля не дожны быть пустыми");
            return result.toString();
        }

        if(!travelTime.matches("[0-9]{2}:[0-9]{2}")) {
            result.put("message", "Неверный формат времени");
            return result.toString();
        }

        Optional<Railway> railway = railwayDAO.getRailway(fromStation, toStation);
        if (!railway.isPresent()) {
            result.put("message", "Связи между станциями не существует");
            return result.toString();
        }

        Optional<RouteNumber> routeNumberFromDB = routeNumberDAO.getRouteNumber(routeNumber);

        if (routeNumberFromDB.isPresent()) {
            return savePath(routeNumberFromDB.get(), toStation, travelTime);

        } else {
            saveFirstPath(routeNumber, fromStation, toStation, travelTime);
            result.put("message", "Путь добавлен");
            return result.toString();
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

    private String savePath(RouteNumber routeNumber, String toStation, String travelTime) {

        Optional<Route> route = routeDAO.getRoute(routeNumber.getNumber(), toStation);
        JSONObject result = new JSONObject();

        if (route.isPresent()) {
            result.put("message", "Путь уже добавлен");
            return result.toString();
        }
        Optional<RouteNumber> routeNumberFromDB = routeNumberDAO.getRouteNumber(routeNumber.getNumber());

        Station station = stationDAO.getStationByName(toStation).get();
        Route routeToSave = new Route(routeNumberFromDB.get(), station, travelTime);
        routeDAO.save(routeToSave);
        result.put("message", "Путь добавлен");
        return result.toString();
    }

    @Override
    public List<RouteNumber> getAllRouteNumbers() {
        return routeNumberDAO.getAllRouteNumber();
    }

    @Override
    public List<Route> getRouteByNumber(String number) {
        return routeDAO.getRouteByNumber(number);
    }

    private boolean isFieldsEmpty(String...strings) {
        for (String s : strings) {
            if (s.equals("")) {
                return true;
            }
        }
        return false;
    }
}