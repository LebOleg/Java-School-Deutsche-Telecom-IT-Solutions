package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Ticket;
import ru.lebedev.SBBProject.model.Timetable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public class TicketDAOImpl implements TicketDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Ticket ticket) {
        entityManager.persist(ticket);
    }
}
