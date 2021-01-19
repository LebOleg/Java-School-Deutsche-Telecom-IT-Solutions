package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Route;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class RouteDAOImpl implements RouteDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Route route) {
        entityManager.persist(route);
    }

    @Override
    public Optional<Route> getRoute(String routeNumber, String toStation) {
        try {
            return Optional.of(
                    entityManager.createQuery("select r from Route r where r.routeNumber.number=:routeNumber and r.station.name=:toStation", Route.class)
                            .setParameter("routeNumber", routeNumber)
                            .setParameter("toStation", toStation)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }


    }
}
