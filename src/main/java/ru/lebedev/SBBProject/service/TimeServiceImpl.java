package ru.lebedev.SBBProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.TrainDAO;
import ru.lebedev.SBBProject.model.Train;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class TimeServiceImpl implements TimerService {
    private Timer timer;
    @Autowired
    private TrainDAO trainDAO;
    @Autowired
    private SimpleTransactionService transactionService;
    private boolean end;

    @Transactional
    @Override
    public void seatsBooking(String t) {
        Train train = trainDAO.getTrainById(Integer.parseInt(t));
        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainDAO.saveTrain(train);
        end = false;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                train.setAvailableSeats(train.getAvailableSeats() + 1);
                transactionService.executeTransaction(() -> {
                    Train bookingTrain = trainDAO.getTrainById(Integer.parseInt(t));
                    bookingTrain.setAvailableSeats(bookingTrain.getAvailableSeats() + 1);
                    trainDAO.saveTrain(bookingTrain);
                });

                end = true;
            }
        };

        timer = new Timer();
        timer.schedule(task, 900000);
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean isEnd() {
        return end;
    }
}
