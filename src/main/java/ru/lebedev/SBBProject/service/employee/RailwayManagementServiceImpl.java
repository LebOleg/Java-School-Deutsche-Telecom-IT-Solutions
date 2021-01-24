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
import java.util.Random;

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
        Random random = new Random(0);

        for(Station s: stations) {
            JSONObject jsonStations = new JSONObject();
            jsonStations.put("id",s.getName());
            jsonStations.put("label", s.getName());
            jsonStations.put("x", random.nextFloat());
            jsonStations.put("y", random.nextFloat());
            jsonStations.put("size", 3);
            jsonArrayStation.put(jsonStations);
        }

        jsonObject.put("nodes", jsonArrayStation);

        JSONArray jsonArrayEdges = new JSONArray();

        for (Railway r: railways) {

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
