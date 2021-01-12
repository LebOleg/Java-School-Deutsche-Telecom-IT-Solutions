package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Timetable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TimetableDAOImpl implements TimetableDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Timetable> getStationTimetable(String station) {
        return entityManager.createQuery("select t from Timetable t where t.station.name =:stationParam order by t.departureTime", Timetable.class)
                .setParameter("stationParam", station)
                .getResultList();

    }
}
