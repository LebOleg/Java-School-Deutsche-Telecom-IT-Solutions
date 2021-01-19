package ru.lebedev.SBBProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.RouteDAO;
import ru.lebedev.SBBProject.dao.RouteNumberDAO;
import ru.lebedev.SBBProject.dao.TimetableDAO;
import ru.lebedev.SBBProject.dao.TrainDAO;
import ru.lebedev.SBBProject.model.RouteNumber;
import ru.lebedev.SBBProject.model.Timetable;
import ru.lebedev.SBBProject.model.Train;
import ru.lebedev.SBBProject.utility.CustomConverter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TimetableManagementServiceImpl implements TimetableManagementService {
    @Autowired
    private TrainDAO trainDAO;
    @Autowired
    private RouteNumberDAO routeNumberDAO;
    @Autowired
    private TimetableDAO timetableDAO;

    @Override
    public List<Train> getAvailableTrains() {
        return trainDAO.getAvailableTrains();
    }

    @Transactional
    @Override
    public void addInTimetable(String params) {

        String[] timetableParams = params.split("&");
        String routeNumber = timetableParams[0].substring(timetableParams[0].indexOf("=") + 1);
        String date = timetableParams[1].substring(timetableParams[1].indexOf("=") + 1);
        String time = timetableParams[2].substring(timetableParams[2].indexOf("=") + 1).replace("%3A", ":");
        String trainNumber = timetableParams[3].substring(timetableParams[3].indexOf("=") + 1);

        LocalDate timetableDate = CustomConverter.convertStringToDate(date);
        LocalTime timetableTime = CustomConverter.convertStringToTime(time);

        RouteNumber routeNumberFromDb = routeNumberDAO.getRouteNumber(routeNumber).get();
        Train train = trainDAO.getTrainByNumber(trainNumber).get();
        train.setRouteNumber(routeNumberFromDb);

        Timetable timetable = new Timetable(train, routeNumberFromDb, timetableTime, timetableDate);
        timetableDAO.saveInTimetable(timetable);
    }
}
