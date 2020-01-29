/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author madse
 */
@Path("animal")
public class AnimalResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     * @return an instance of java.lang.String
     */
    @GET
    // @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello";
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String randomAnimal () {
        List<Animal> animals = new ArrayList<>();
        Animal dog = new Animal("Dog", 2012, "Bark");
        Animal cat = new Animal("Cat", 2018, "Miaw");
        Animal bird = new Animal("Bird", 2020, "Pip");
        animals.add(dog);
        animals.add(cat);
        animals.add(bird);
        
        int randomNumber = (int) (Math.random()*(animals.size()));
        return new Gson().toJson(animals.get(randomNumber));
    }
    
    
}
