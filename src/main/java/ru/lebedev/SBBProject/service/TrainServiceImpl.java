package ru.lebedev.SBBProject.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.SBBProject.dao.TrainDAO;

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainDAO trainDAO;

    @Override
    public String getAvailableSeats(String trainNumber) {
        JSONObject jsonTrain = new JSONObject(trainNumber);
        trainNumber = jsonTrain.getString("train");

        Integer seats = trainDAO.getCurrentAvailableSeats(Integer.parseInt(trainNumber));

        JSONObject result = new JSONObject();
        result.put("seats", seats);
        return result.toString();
    }

}
