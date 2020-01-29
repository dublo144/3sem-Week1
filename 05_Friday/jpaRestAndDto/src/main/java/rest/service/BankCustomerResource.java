package rest.service;

import com.google.gson.Gson;
import entities.BankCustomer;
import facades.CustomerFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("bankcustomer")
public class BankCustomerResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    CustomerFacade facade =  CustomerFacade.getCustomerFacade(emf);
    Gson gson = new Gson();

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers () {
        return gson.toJson(facade.getAllBankCustomers());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String geCustomerById (@PathParam("id") Long id) {
        return gson.toJson(facade.getCustomerById(id));
    }
}
