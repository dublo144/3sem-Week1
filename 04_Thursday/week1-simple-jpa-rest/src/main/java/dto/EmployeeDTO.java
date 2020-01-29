/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author madse
 */
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String name; 
    private String address;
    private double salary;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
        this.salary = employee.getSalary();
    }
    
    
}
