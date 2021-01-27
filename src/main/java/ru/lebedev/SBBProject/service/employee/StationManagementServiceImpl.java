package ru.lebedev.SBBProject.service.employee;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.RailwayDAO;
import ru.lebedev.SBBProject.dao.StationDAO;
import ru.lebedev.SBBProject.model.Railway;
import ru.lebedev.SBBProject.model.Station;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class StationManagementServiceImpl implements StationManagementService {
    @Autowired
    private StationDAO stationDAO;
    @Autowired
    private RailwayDAO railwayDAO;

    @Transactional
    @Override
    public String createStation(String JSONStation) {
        JSONObject jsonObject = new JSONObject(JSONStation);
        String station = jsonObject.getString("station").trim();

        JSONObject result = new JSONObject();

        if (station.equals("")) {
            result.put("message", "Поле должно быть непустым");

            return result.toString();
        }

        Optional<Station> stationDB = stationDAO.getStationByName(station);
        if (!stationDB.isPresent()) {
            stationDAO.save(new Station(station));
            result.put("message", "Станция создана");

            return result.toString();
        } else {
            result.put("message", "Cтанция уже существует");
            return result.toString();
        }
    }

    @Transactional
    @Override
    public String createConnection(String stations) {
        JSONObject jsonStation = new JSONObject(stations);
        String fromStation = jsonStation.getString("fromStation");
        String toStation = jsonStation.getString("toStation");

        Optional<Station> fromStationDb = stationDAO.getStationByName(fromStation);
        Optional<Station> toStationDb = stationDAO.getStationByName(toStation);

        JSONObject result = new JSONObject();

        if (!fromStationDb.isPresent()) {
            result.put("message", "Cтанции " + fromStation + " не сущетсвует");

            return result.toString();
        }
        if (!toStationDb.isPresent()) {
            result.put("message", "Cтанции " + toStation + " не сущетсвует");

            return result.toString();
        }

        Optional<Railway> railway = railwayDAO.getRailway(fromStation, toStation);
        if (!railway.isPresent()) {
            railwayDAO.save(new Railway(fromStationDb.get(), toStationDb.get()));
            result.put("message", "Связь создана");

            return result.toString();
        } else {
            result.put("message", "Связь уже существует");

            return result.toString();
        }
    }
}
