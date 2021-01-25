package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.RouteNumber;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RouteNumberDAOImpl implements RouteNumberDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<RouteNumber> getRouteNumber(String number) {
        try {
            return Optional.of(
                    entityManager.createQuery("select rn from RouteNumber rn where rn.number=:number", RouteNumber.class)
                            .setParameter("number", number)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void saveRouteNumber(RouteNumber routeNumber) {
        entityManager.persist(routeNumber);
    }

    @Override
    public List<RouteNumber> getAllRouteNumber() {
        return entityManager.createQuery("select rn from RouteNumber rn", RouteNumber.class)
                .getResultList();
    }
}
