package com.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.entity.Product;

public class ProductReository {
	EntityManager entityManager;

	public ProductReository() {
		super();
		entityManager = Persistence.createEntityManagerFactory("OnGK_Java").createEntityManager();
	}
	public Product save(Product p)
	{
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
	public List<Product> getAll()
	{
		String sql = "from Product";
        List<Product> ls = entityManager.createQuery(sql).getResultList();
        return ls;
	}
	

}
