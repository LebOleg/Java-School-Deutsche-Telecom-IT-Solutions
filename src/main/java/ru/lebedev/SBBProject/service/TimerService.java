package ru.lebedev.SBBProject.service;

import java.util.Timer;

public interface TimerService {

    void seatsBooking(String train);

    Timer getTimer();

    boolean isEnd();
}
