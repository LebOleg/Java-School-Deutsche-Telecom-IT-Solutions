package ru.lebedev.SBBProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.TrainDAO;
import ru.lebedev.SBBProject.model.Train;

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
}
