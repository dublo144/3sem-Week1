/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.Test;

/**
 *
 * @author thomas
 */
public class CustomerFacadeTest {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu_test");
    private static final CustomerFacade CF = CustomerFacade.getCustomerFacade(EMF);
    public CustomerFacadeTest () {
    }
    
    @BeforeAll
    public static void setUpClass() {
//        Add code to setup entities for test before running any test methods
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run
    }
    
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void getCustomerById () {
        CustomerDTO customer = CF.getCustomerById((long) 2);
        String expectedName = "Mads Brandt";
        String actualName = customer.getFullName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void getCustomerByName () {
        List<CustomerDTO> customerDTOS = CF.getCustomerByName("Mads");
        String expectedName = "Mads Brandt";
        String actualName = customerDTOS.get(0).getFullName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void addCustomer () {
        int currentNoOfCustomers = EMF.createEntityManager().createQuery("SELECT c FROM BankCustomer c").getResultList().size();
        BankCustomer customer = new BankCustomer("Jane", "Doe", "123124512", 1000, 3, "Info");
        CF.addCustomer(customer);
        int expectedNoOfCustomers = currentNoOfCustomers + 1;
        int actualNoOfCustomers = EMF.createEntityManager().createQuery("SELECT c FROM BankCustomer c").getResultList().size();
        assertEquals(expectedNoOfCustomers, actualNoOfCustomers);
    }

    @Test
    public void getAllBankCustomers () {
    }
}
