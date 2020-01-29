/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Ignore;

/**
 *
 * @author madse
 */
public class CustomerDaoTest {
    
    @Test
    @Ignore
    public void addCustomer () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerDao dao = CustomerDao.getCustomerDao(emf);

        long expected = 1;
        dao.addCustomer("John", "Doe");
        long actual = (Long) emf.createEntityManager().createQuery("SELECT count(c) from Customer c").getSingleResult();

        assertEquals(expected, actual);
    }

    @Test
    public void findByID () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerDao dao = CustomerDao.getCustomerDao(emf);

        String expected = "John";
        String actual = dao.findByID(3).getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    public void getAllCustomers () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerDao dao = CustomerDao.getCustomerDao(emf);

        int expected = 1;
        int actual = dao.getAllCustomers().size();
        assertEquals(expected, actual);
    }

    @Test
    public void findByLastName () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerDao dao = CustomerDao.getCustomerDao(emf);

        String expected = "John";
        String actual = dao.findByLastName("Doe").get(0).getFirstName();

        assertEquals(expected, actual);
    }

    @Test
    public void getNumberOfCustomers () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerDao dao = CustomerDao.getCustomerDao(emf);

        long expected = 1;
        long actual = dao.getNumberOfCustomers();
        assertEquals(expected, actual);
    }

    
    
}
