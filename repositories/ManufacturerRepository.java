package com.example.productmanagement.repositories;

import com.example.productmanagement.entities.Manufacturer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import java.util.List;

public class ManufacturerRepository {

    EntityManager entityManager;
    public ManufacturerRepository() {
        entityManager = Persistence.createEntityManagerFactory("ProductManagement")
                .createEntityManager();
    }

    public List<Manufacturer> findAll() {
        String sql = "from Manufacturer" ;
        List<Manufacturer> lst = entityManager.createQuery(sql).getResultList();
        return lst;
    }

    public Manufacturer save(Manufacturer manufacturer) {
        EntityTransaction transaction =entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(manufacturer);
            transaction.commit();
        } catch(Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return null;
        }
        return manufacturer;
    }

    public Manufacturer findManufacturerById(String id){
        return entityManager.find(Manufacturer.class,Long.parseLong(id));
    }
    public boolean delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Manufacturer manufacturer =findManufacturerById(id);
            entityManager.remove(manufacturer);
            transaction.commit();
        } catch(Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public Manufacturer update(Manufacturer manufacturer) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(manufacturer);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return null;
        }
        return manufacturer;
    }


}
