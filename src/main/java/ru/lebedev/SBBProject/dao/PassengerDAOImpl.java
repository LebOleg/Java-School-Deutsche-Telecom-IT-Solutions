package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class PassengerDAOImpl implements PassengerDAO{
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void save(Passenger p) {
        entityManager.persist(p);
    }

    @Override
    public Optional<Passenger> getPassengerIfExists(Passenger p) {
        try {
            return Optional.of(
                    entityManager.createQuery("select p from Passenger p where " +
                            "p.name=:name and p.lastName=:lastName and p.middleName=:middleName and p.birthday=:birthday " +
                            "and p.passportNumber=:passportNumber and p.user.username=:username", Passenger.class)
                            .setParameter("name", p.getName())
                            .setParameter("lastName", p.getLastName())
                            .setParameter("middleName", p.getMiddleName())
                            .setParameter("birthday", p.getBirthday())
                            .setParameter("passportNumber", p.getPassportNumber())
                            .setParameter("username", p.getUser().getUsername())
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }


    }
}
