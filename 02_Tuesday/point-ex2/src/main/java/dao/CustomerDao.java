/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author madse
 */
public class CustomerDao {
    private static EntityManagerFactory emf;
    private static CustomerDao instance;
    
    public static CustomerDao getCustomerDao (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerDao();
        }
        return instance;
    }
    
    public Customer addCustomer (String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }       
    }
    
    public Customer findByID (long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public List<Customer> getAllCustomers () {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName (String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c from Customer c WHERE c.lastName = :lastName", Customer.class);
            query.setParameter("lastName", lastName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public long getNumberOfCustomers () {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT count(c) from Customer c");
            return (Long)query.getSingleResult();
        } finally {
            em.close();
        }
    }
}
