package ru.lebedev.SBBProject.service.employee;

import org.json.JSONObject;

public interface StationManagementService {

    String createStation(String stationName);

    String createConnection(String stations);

}
