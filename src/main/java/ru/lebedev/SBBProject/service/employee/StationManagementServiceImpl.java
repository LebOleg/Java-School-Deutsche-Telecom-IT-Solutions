package ru.lebedev.SBBProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.RailwayDAO;
import ru.lebedev.SBBProject.dao.StationDAO;
import ru.lebedev.SBBProject.model.Railway;
import ru.lebedev.SBBProject.model.Station;

import java.util.Optional;

@Service
public class StationManagementServiceImpl implements StationManagementService{
    @Autowired
    private StationDAO stationDAO;
    @Autowired
    private RailwayDAO railwayDAO;

    @Transactional
    @Override
    public Boolean createStation(String stationName){

        if(stationName.contains("=")) {
            stationName = stationName.substring(stationName.indexOf("=") + 1);
        }

        stationName = stationName.trim();
        if(stationName.equals("") || stationName == null) {
            return false;
        }

        Optional<Station> station = stationDAO.getStationByName(stationName);
        if(!station.isPresent()) {
            stationDAO.save(new Station(stationName));
            return true;
        }
        else {
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean createConnection(String stations) {
        String[] station = stations.split("&");
        String fromStation = station[0].substring(station[0].indexOf("=") + 1);
        String toStation =  station[1].substring(station[1].indexOf("=") + 1);

        Optional<Station> fromStationDb = stationDAO.getStationByName(fromStation);
        Optional<Station> toStationDb = stationDAO.getStationByName(toStation);

        if(!fromStationDb.isPresent()) {
            return false;
        }
        if(!toStationDb.isPresent()) {
            return false;
        }

        Optional<Railway> railway = railwayDAO.getRailway(fromStation, toStation);
        if(!railway.isPresent()) {
            railwayDAO.save(new Railway(fromStationDb.get(), toStationDb.get()));
            return true;
        } else {
            return false;
        }

    }
}
