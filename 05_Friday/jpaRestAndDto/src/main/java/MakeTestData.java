import entities.BankCustomer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MakeTestData {
    public static void main (String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        BankCustomer customer1 = new BankCustomer("Mads", "Brandt", "12345", 1000, 1, "Some internal info");
        BankCustomer customer2 = new BankCustomer("John", "John", "12311231", 1000, 2, "Some info");
        try {
            em.getTransaction().begin();
            em.persist(customer1);
            em.persist(customer2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
