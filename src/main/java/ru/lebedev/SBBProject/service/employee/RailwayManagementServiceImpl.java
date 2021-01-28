package ru.lebedev.SBBProject.service.employee;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.SBBProject.dao.RailwayDAO;
import ru.lebedev.SBBProject.dao.StationDAO;
import ru.lebedev.SBBProject.model.Railway;
import ru.lebedev.SBBProject.model.Station;

import java.util.List;

@Service
public class RailwayManagementServiceImpl implements RailwayManagementService {
    @Autowired
    private RailwayDAO railwayDAO;
    @Autowired
    private StationDAO stationDAO;


    @Override
    public JSONObject getRailwayGraph() {
        List<Station> stations = stationDAO.getAllStations();
        List<Railway> railways = railwayDAO.getAllRailways();

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArrayStation = new JSONArray();

        int angle = 0;
        double radius = 0.3f;

        for (Station s : stations) {

            if (angle == 320) {
                angle = 0;
                radius = radius + 0.3f;
            }

            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);

            JSONObject jsonStations = new JSONObject();
            jsonStations.put("id", s.getName());
            jsonStations.put("label", s.getName());
            jsonStations.put("x", x);
            jsonStations.put("y", y);
            jsonStations.put("size", 3);
            jsonArrayStation.put(jsonStations);
            angle = angle + 40;
        }

        jsonObject.put("nodes", jsonArrayStation);

        JSONArray jsonArrayEdges = new JSONArray();

        for (Railway r : railways) {

            JSONObject jsonEdge = new JSONObject();
            jsonEdge.put("id", r.getId());
            jsonEdge.put("source", r.getFromStation().getName());
            jsonEdge.put("target", r.getToStation().getName());
            jsonArrayEdges.put(jsonEdge);
        }

        jsonObject.put("edges", jsonArrayEdges);

        return jsonObject;
    }
}
