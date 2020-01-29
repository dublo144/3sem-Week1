package rest.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeDao dao =  EmployeeDao.getEmployeeDao(emf);

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<Employee> employees = dao.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>(); 
        for (Employee employee : employees) {
            employeeDTOs.add(new EmployeeDTO(employee));
        }                
        return new Gson().toJson(employeeDTOs);
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") Long id) {
        Employee employee = dao.getEmployeeById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO(employee);
        return new Gson().toJson(employeeDTO);
    }
    
    @GET
    @Path("higestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaidEmployee() {
        List<Employee> employees = dao.getEmployeesWithHighestSalary();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>(); 
        for (Employee employee : employees) {
            employeeDTOs.add(new EmployeeDTO(employee));
        }                
        return new Gson().toJson(employeeDTOs);
    }
    
    @GET
    @Path("name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeByName(@PathParam("name") String name) {
        List<Employee> employees = dao.getEmployeeByName(name);
        List<EmployeeDTO> employeeDTOs = new ArrayList<>(); 
        for (Employee employee : employees) {
            employeeDTOs.add(new EmployeeDTO(employee));
        }                
        return new Gson().toJson(employeeDTOs);
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createEmployee(String request) {
        List<EmployeeDTO> employeeDTOs = new Gson().fromJson(request, new TypeToken<List<EmployeeDTO>>() {}.getType());
        for (EmployeeDTO employeeDTO : employeeDTOs) {
            String name = employeeDTO.getName();
            String address = employeeDTO.getAddress();
            double salary = employeeDTO.getSalary();
            dao.createEmployee(name, address, salary);
        }
    }
}
