package facades;

import dto.CustomerDTO;
import entities.BankCustomer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade () {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerById (Long id) {
        EntityManager em = getEntityManager();
        BankCustomer bankCustomer = em.find(BankCustomer.class, id);
        return new CustomerDTO(bankCustomer);
    }

    public List<CustomerDTO> getCustomerByName (String name) {
        EntityManager em = getEntityManager();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        TypedQuery<BankCustomer> query = em.createQuery("SELECT c FROM BankCustomer c WHERE c.firstName LIKE :name OR c.lastName LIKE :name", BankCustomer.class);
        query.setParameter("name", name);
        List<BankCustomer> bankCustomers = query.getResultList();
        for (BankCustomer bankCustomer : bankCustomers) {
            customerDTOS.add(new CustomerDTO(bankCustomer));
        }
        return customerDTOS;
    }

    public BankCustomer addCustomer (BankCustomer customer) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return customer;
    }

    public List<BankCustomer> getAllBankCustomers () {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT c FROM BankCustomer c").getResultList();
    }

}
