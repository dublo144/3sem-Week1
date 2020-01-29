/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author madse
 */
public class EmployeeDao {
    
    private static EmployeeDao instance;
    private static EntityManagerFactory emf;
    
    private EmployeeDao() {}
    
    public static EmployeeDao getEmployeeDao (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeDao();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Employee getEmployeeById (Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Employee> getEmployeeByName (String name) {
        EntityManager em = getEntityManager();
        
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name LIKE CONCAT('%',:name,'%')", Employee.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
    
    public List<Employee> getAllEmployees () {
        EntityManager em = getEntityManager();
        
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }
    
    public List<Employee> getEmployeesWithHighestSalary () {
        EntityManager em = getEntityManager();
        
        TypedQuery<Employee> query = em.createQuery(
                "SELECT e from Employee e where e.salary = (SELECT MAX(e.salary) FROM Employee e)", 
                Employee.class);
        return query.getResultList();
    }
    
    public Employee createEmployee (String name, String address, double salary) {
        EntityManager em = getEntityManager();
        Employee employee = new Employee(name, address, salary);
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return employee;
    }
}
