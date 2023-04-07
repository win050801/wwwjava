package com.example.productmanagement.repositories;

import com.example.productmanagement.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepository {
    EntityManager entityManager;

    public ProductRepository() {
        entityManager = Persistence.createEntityManagerFactory("ProductManagement").createEntityManager();
    }

    public Product save(Product p) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(p);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return null;
        }
        return p;
    }

    public List<Product> findAll() {
        String sql = "from Product";
        List<Product> ls = entityManager.createQuery(sql).getResultList();
        return ls;
    }


    public boolean removeProduct(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(getProductById(id));
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateProduct(Product p) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(p);
            transaction.commit();
        }catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    public Product getProductById(String id){
        return entityManager.find(Product.class,Long.parseLong(id));
    }

}
