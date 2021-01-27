package ru.lebedev.SBBProject.service.employee;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.RouteNumberDAO;
import ru.lebedev.SBBProject.dao.TimetableDAO;
import ru.lebedev.SBBProject.dao.TrainDAO;
import ru.lebedev.SBBProject.dto.TrainDTO;
import ru.lebedev.SBBProject.model.RouteNumber;
import ru.lebedev.SBBProject.model.Timetable;
import ru.lebedev.SBBProject.model.Train;
import ru.lebedev.SBBProject.utility.CustomConverter;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainManagementServiceImpl implements TrainManagementService {

    @Autowired
    private TrainDAO trainDAO;
    @Autowired
    private RouteNumberDAO routeNumberDAO;
    @Autowired
    private TimetableDAO timetableDAO;

    @Transactional
    @Override
    public String createTrain(String trainInfo) {
        JSONObject jsonTrain = new JSONObject(trainInfo);
        String seatsQuantity = jsonTrain.getString("seats");
        String route = jsonTrain.getString("route");
        String date = jsonTrain.getString("date");
        String time = jsonTrain.getString("time");

        JSONObject result = new JSONObject();

        if (isFieldsEmpty(seatsQuantity, route, date, time)) {
            result.put("message", "Поля должны быть непустыми");
            return result.toString();
        }

        int seats = 0;
        if (isNumber(seatsQuantity)) {
            seats = Integer.parseInt(seatsQuantity);
        } else {
            result.put("message", "Количество мест должно быть числом");
            return result.toString();
        }

        if (seats == 0) {
            result.put("message", "Количество мест должно быть больше 0");
            return result.toString();
        }

        LocalDateTime timetableDateAndTime = CustomConverter.convertStringToTimeAndDate(time, date);
        LocalDateTime today = LocalDateTime.now();
        if(timetableDateAndTime.isBefore(today.plus(1, ChronoUnit.DAYS))) {
            result.put("message", "Неверная дата");
            return result.toString();
        }

        RouteNumber routeNumberFromDb = routeNumberDAO.getRouteNumber(route).get();
        Train train = new Train(seats, routeNumberFromDb);
        Timetable timetable = new Timetable(train, routeNumberFromDb, timetableDateAndTime);
        timetableDAO.saveInTimetable(timetable);
        result.put("message", "Поезд создан");
        return result.toString();
    }

    private boolean isNumber(String supposedNumber) {

        return supposedNumber.matches("[0-9]+");
    }

    @Override
    public List<TrainDTO> getAllTrains() {
        List<Tuple> tupleTrains = trainDAO.getAllTrains();
        List<TrainDTO> trains = new ArrayList<>();

        for (Tuple t : tupleTrains) {
            int id = t.get("id", Integer.class);
            int availableSeats = t.get("availableSeats", Integer.class);
            String routeNumber = t.get("routeNumber", String.class);

            TrainDTO trainDTO = new TrainDTO(id, availableSeats, routeNumber);

            trains.add(trainDTO);

        }

        return trains;
    }

    private boolean isFieldsEmpty(String...strings) {
        for(String s: strings) {
            if(s.equals("")) {
                return true;
            }
        }
        return false;
    }
}