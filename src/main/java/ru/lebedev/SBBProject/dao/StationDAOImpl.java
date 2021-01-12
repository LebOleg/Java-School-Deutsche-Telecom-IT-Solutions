package ru.lebedev.SBBProject.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
}