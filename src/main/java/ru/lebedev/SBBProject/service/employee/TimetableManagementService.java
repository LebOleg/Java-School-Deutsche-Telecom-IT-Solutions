package ru.lebedev.SBBProject.service.employee;

import ru.lebedev.SBBProject.model.Train;

import java.util.List;

public interface TimetableManagementService {

    List<Train> getAvailableTrains();

    void addInTimetable(String params);
}
