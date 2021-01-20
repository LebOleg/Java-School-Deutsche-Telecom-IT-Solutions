package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Route;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
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
    public List<String> getSuitableRoutes(String fromStation, String toStation) {

        Query query = entityManager.createQuery(
                "select r1.routeNumber.number from Route r1 join Route r2 on " +
                        "r1.routeNumber = r2.routeNumber and r1.station.name = :fromStation and r2.station.name =:toStation");

        query.setParameter("fromStation", fromStation);
        query.setParameter("toStation", toStation);

        return query.getResultList();


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
