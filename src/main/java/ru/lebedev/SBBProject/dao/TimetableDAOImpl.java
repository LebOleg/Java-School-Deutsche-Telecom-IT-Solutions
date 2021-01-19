package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.model.Ticket;
import ru.lebedev.SBBProject.model.Timetable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public class TimetableDAOImpl implements TimetableDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Timetable> getStationTimetable(String station) {

        return entityManager.createQuery("select t from Timetable t " +
                "where t.station.name =:stationParam order by t.arrivalTime", Timetable.class)
                .setParameter("stationParam", station)
                .getResultList();

    }

    @Override
    public List<Ticket> getAvailableTicket(SearchTicketAttributes ticketAttributes, LocalDateTime fromTime, LocalDateTime toTime) {

        TypedQuery<Ticket> query = entityManager.createQuery("select new Ticket(t.station, t1.station, t.departureTime, t1.arrivalTime,t.train) from Timetable t " +
                "join Timetable t1 on t.train=t1.train and t1.station.name=:destinationStation and t.station.name=:sourceStation " +
                "where t.departureTime between :fromTime and :toTime and t1.arrivalTime > t.departureTime", Ticket.class);

        query.setParameter("destinationStation", ticketAttributes.getToStation());
        query.setParameter("sourceStation", ticketAttributes.getFromStation());
        query.setParameter("fromTime", fromTime);
        query.setParameter("toTime", toTime);

        return query.getResultList();
    }

    @Override
    public void saveInTimetable(Timetable timetable) {
        entityManager.persist(timetable);
    }
}