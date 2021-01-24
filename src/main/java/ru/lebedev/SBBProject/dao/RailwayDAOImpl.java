package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Railway;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
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
                       "r.fromStation.name in (:fromStation, :toStation) and r.toStation.name in (:fromStation, :toStation)", Railway.class)
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

    @Override
    public List<Railway> getAllRailways() {

        List<Railway> railways = entityManager.createQuery("select r from Railway r", Railway.class)
                .getResultList();

        return railways;
    }
}
