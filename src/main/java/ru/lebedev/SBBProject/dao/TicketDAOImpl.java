package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Ticket ticket) {
        entityManager.persist(ticket);
    }

    @Override
    public List<Ticket> getUsersTicket(List<String> passengers) {
        return entityManager.createQuery("select t from Ticket t where t.passenger.id in :passengers", Ticket.class)
                .setParameter("passengers", passengers)
                .getResultList();
    }
}
