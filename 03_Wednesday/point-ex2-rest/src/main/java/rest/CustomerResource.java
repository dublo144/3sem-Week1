/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dao.CustomerDao;
import entities.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author madse
 */
@Path("customer")
public class CustomerResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.point.ex2.rest.CustomerResource
     * @return an instance of java.lang.String
     */
    @GET
    // @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello";
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerDao dao = CustomerDao.getCustomerDao(emf);
        
        List<Customer> customers = dao.getAllCustomers();
        return new Gson().toJson(customers); 
    } 
    
    @GET
    @Path("random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomCustomer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerDao dao = CustomerDao.getCustomerDao(emf);
        
       List<Customer> customers = dao.getAllCustomers();
       int randomNumber = (int) (Math.random()*(customers.size()));
       return new Gson().toJson(customers.get(randomNumber));
    }
    
    @GET
    @Path("/{id}")
    public String getCustomerById (@PathParam("id") long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerDao dao = CustomerDao.getCustomerDao(emf);
        
        Customer customer = dao.findByID(id);
        return new Gson().toJson(customer);
    }

    /**
     * PUT method for updating or creating an instance of CustomerResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
