package ru.lebedev.SBBProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.TrainDAO;
import ru.lebedev.SBBProject.dto.TrainDTO;
import ru.lebedev.SBBProject.model.Train;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainManagementServiceImpl implements TrainManagementService {

    @Autowired
    private TrainDAO trainDAO;

    @Transactional
    @Override
    public Boolean createTrain(String trainInfo) {
        String[] trains = trainInfo.split("&");
        String seatsQuantity = trains[0].substring(trains[0].indexOf("=") + 1);
        String trainQuantity =  trains[1].substring(trains[1].indexOf("=") + 1);
        int seats = 0;
        int trainsNumber = 0;

        if(isNumber(seatsQuantity) && isNumber(trainQuantity)) {
            seats = Integer.parseInt(seatsQuantity);
            trainsNumber = Integer.parseInt(trainQuantity);

        } else return false;

        for (int i = 0; i < trainsNumber; i++) {
            Train train = new Train(seats);
            trainDAO.saveTrain(train);
        }


        return true;
    }

    private boolean isNumber(String supposedNumber) {

        return supposedNumber.matches("[0-9]+");
    }

    @Override
    public List<TrainDTO> getAllTrains() {
        List<Tuple> tupleTrains = trainDAO.getAllTrains();
        List<TrainDTO> trains = new ArrayList<>();

        for(Tuple t: tupleTrains) {
            int id = t.get("id", Integer.class);
            int availableSeats = t.get("availableSeats", Integer.class);
            String routeNumber = t.get("routeNumber", String.class);

            TrainDTO trainDTO = new TrainDTO(id, availableSeats, routeNumber);

            trains.add(trainDTO);

        }

        return trains;
    }
}
