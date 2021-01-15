package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class TrainDAOImpl implements TrainDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer getCurrentAvailableSeats(String trainNumber) {
        TypedQuery<Integer> query = entityManager.createQuery("select t.availableSeats from Train t " +
                "where t.number=:trainNumber", Integer.class);
        query.setParameter("trainNumber", trainNumber);

        return query.getSingleResult();
    }
}
