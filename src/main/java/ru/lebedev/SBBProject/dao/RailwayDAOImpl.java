package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Railway;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class RailwayDAOImpl implements RailwayDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Railway> getRailway(String fromStation, String toStation) {

        try {
            return Optional.of(
               entityManager.createQuery("select r from Railway r where " +
                       "r.fromStation.name=:fromStation and r.toStation.name=:toStation", Railway.class)
                       .setParameter("fromStation", fromStation)
                       .setParameter("toStation", toStation)
                       .getSingleResult()
            );

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Railway railway) {
        entityManager.persist(railway);
    }
}
