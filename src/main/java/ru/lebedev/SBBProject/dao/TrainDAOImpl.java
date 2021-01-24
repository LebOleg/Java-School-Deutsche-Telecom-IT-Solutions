package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Train;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainDAOImpl implements TrainDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer getCurrentAvailableSeats(Integer trainNumber) {
        TypedQuery<Integer> query = entityManager.createQuery("select t.availableSeats from Train t " +
                "where t.id=:trainNumber", Integer.class);
        query.setParameter("trainNumber", trainNumber);

        return query.getSingleResult();
    }

    @Override
    public Train getTrainById(Integer number) {



            return entityManager.createQuery("select t from Train t where t.id=:number", Train.class)
                            .setParameter("number", number)
                            .getSingleResult();


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

    @Override
    public List<Tuple> getAllTrains() {
        return entityManager.createNativeQuery("select id, seats_available as availableSeats, route_number as routeNumber from train", Tuple.class).getResultList();
    }
}
