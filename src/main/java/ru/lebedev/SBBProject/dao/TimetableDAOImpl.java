package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.dto.StationTimetableDTO;
import ru.lebedev.SBBProject.model.Ticket;
import ru.lebedev.SBBProject.model.Timetable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public class TimetableDAOImpl implements TimetableDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tuple> getStationTimetable(String station, LocalDateTime startDay, LocalDateTime endDay) {

        Query query = entityManager.createNativeQuery("Select tq.date as timetableDate, tq.route_number as routeNumber from " +
                "(SELECT addtime(t.begin_time, r.travel_time) as date, t.route_number, r.travel_time FROM " +
                "sbb.timetable as t join sbb.route as r on t.route_number=r.route_number and r.station_name = :station) as tq where " +
                "tq.date > :startDay and tq.date < :endDay order by tq.date", Tuple.class);

        query.setParameter("station", station);
        query.setParameter("startDay", startDay);
        query.setParameter("endDay", endDay);


        List<Tuple> timetableForStation =  query.getResultList();

        return timetableForStation;

    }

    @Override
    public List<Tuple> getAvailableTicket(List<String> suitableRoutes, LocalDateTime fromTime, LocalDateTime toTime, String fromStation, String toStation) {
        Query query = entityManager.createNativeQuery("select addtime(t.begin_time, r.travel_time) as time, t.train_number as trainNumber, t.route_number as routeNumber from Timetable t " +
                "join Route r on t.route_number = r.route_number and r.station_name in (:sourceStation,:destinationStation) where t.route_number in :routeList and t.begin_time between :fromTime and :toTime order by t.train_number", Tuple.class);

        query.setParameter("destinationStation", toStation);
        query.setParameter("sourceStation", fromStation);
        query.setParameter("routeList", suitableRoutes);
        query.setParameter("fromTime", fromTime);
        query.setParameter("toTime", toTime);

        return query.getResultList();
    }

    @Override
    public void saveInTimetable(Timetable timetable) {
        entityManager.persist(timetable);
    }
}