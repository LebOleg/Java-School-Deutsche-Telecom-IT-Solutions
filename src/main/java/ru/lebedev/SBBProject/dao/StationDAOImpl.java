package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;
import ru.lebedev.SBBProject.model.Station;
import ru.lebedev.SBBProject.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class StationDAOImpl implements StationDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String getAutofilledStation(String partName) {
        String strQuery = "select a.name from station as a where (a.name regexp" + "'^" + partName + ".*')";
        Query query = entityManager.createNativeQuery(strQuery);
        String station = (String) query.getSingleResult();
        return station;
    }

    @Override
    public Optional<Station> getStationByName(String name) {
        try {
            return Optional.of(
                    entityManager.createQuery("select s from Station s where s.name =: station", Station.class)
                            .setParameter("station", name)
                            .getSingleResult()
            );
        } catch (NoResultException e) {

            return Optional.empty();
        }
    }

    @Override
    public void save(Station station) {
        entityManager.persist(station);
    }

    @Override
    public List<Station> getAllStations() {

        List<Station> stations = entityManager.createQuery("select s from Station s", Station.class)
                .getResultList();

        return stations;
    }
}