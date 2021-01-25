package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Passenger;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class PassengerDAOImpl implements PassengerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Passenger p) {
        entityManager.persist(p);
    }

    @Override
    public List<String> getPassengerId(String username) {
        Query query = entityManager.createQuery("select p.id from Passenger p where p.user.username =: username")
                .setParameter("username", username);

        return query.getResultList();
    }

    @Override
    public List<Passenger> getPassengersOnTrain(Integer trainNumber) {
        return entityManager.createQuery("select t.passenger from Ticket t where t.train.id=:trainNumber", Passenger.class)
                .setParameter("trainNumber", trainNumber)
                .getResultList();
    }

    @Override
    public Boolean passengerOnTrainExists(Passenger p) {

        TypedQuery<Boolean> query = entityManager.createQuery("SELECT (case when (count(t) > 0) then true else false end) FROM Ticket t join Passenger p on t.passenger.id = p.id where t.passenger=:passenger group by t.train.id having count(t) > 0", Boolean.class)
                .setParameter("passenger", p);

        return query.getSingleResult();
    }

    @Override
    public Optional<Passenger> getPassengerIfExists(Passenger p) {
        try {
            return Optional.of(
                    entityManager.createQuery("select p from Passenger p where " +
                            "p.name=:name and p.lastName=:lastName and p.middleName=:middleName and p.birthday=:birthday " +
                            "and p.passportNumber=:passportNumber", Passenger.class)
                            .setParameter("name", p.getName())
                            .setParameter("lastName", p.getLastName())
                            .setParameter("middleName", p.getMiddleName())
                            .setParameter("birthday", p.getBirthday())
                            .setParameter("passportNumber", p.getPassportNumber())
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
