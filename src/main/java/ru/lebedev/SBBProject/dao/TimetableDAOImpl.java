package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Timetable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Repository
public class TimetableDAOImpl implements TimetableDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Timetable> getStationTimetable(String station) {
        return entityManager.createQuery("select t from Timetable t where t.station.name =:stationParam order by t.arrivalTime", Timetable.class)
                .setParameter("stationParam", station)
                .getResultList();

    }

    @Override
    public List<Timetable> getFirstStationInPath(String station, LocalDateTime fromTime, LocalDateTime toTime) {
        TypedQuery<Timetable> query = entityManager.createQuery("select t from Timetable t where t.station.name=:station and t.departureTime between :fromTime and :toTime", Timetable.class);
        query.setParameter("station", station);
        query.setParameter("fromTime", fromTime);
        query.setParameter("toTime", toTime);

        return query.getResultList();
    }

    @Override
    public List<Timetable> getLastStationInPath(String station, LocalDateTime fromTime, Set<String> trainNumber) {
        TypedQuery<Timetable> query = entityManager.createQuery("select t from Timetable t where t.station.name=:station and t.arrivalTime > :fromTime and t.train.number in :trains", Timetable.class);
        query.setParameter("station", station);
        query.setParameter("fromTime", fromTime);
        query.setParameter("trains", trainNumber);
        return query.getResultList();

    }


//    public List<Timetable> getFirstStationInPath(String station, LocalTime fromTime, LocalTime toTime, LocalDate date) {
//        TypedQuery<Timetable> query = entityManager.createQuery("select t from Timetable t where (t.station.name =: station)  and t.date =: date and t.arrivalTime > :fromTime", Timetable.class);
//        query.setParameter("station", station);
//        query.setParameter("fromTime", fromTime);
////        query.setParameter("toTime", toTime);
//        query.setParameter("date", date);
//
//        return query.getResultList();
//    }


}
