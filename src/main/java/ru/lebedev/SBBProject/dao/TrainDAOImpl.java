package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Train;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Train> getTrainByNumber(String number) {
        Integer intNumber = Integer.valueOf(number);

        try {
            return Optional.of(
                    entityManager.createQuery("select t from Train t where t.id=:number", Train.class)
                            .setParameter("number", intNumber)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateAvailableSeats(Train train) {
        entityManager.merge(train);
    }

    @Override
    public void saveTrain(Train train) {

        entityManager.persist(train);
    }

    @Override
    public List<Train> getAvailableTrains() {

        return entityManager.createQuery("select t from Train t where t.routeNumber.number=null", Train.class)
                .getResultList();
    }
}
