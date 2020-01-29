package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class BankCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String accountNumber;
    private double balance;
    private int customerRanking;
    private String internalInfo;

    public BankCustomer (String firstName, String lastName, String accountNumber, double balance, int customerRanking, String internalInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerRanking = customerRanking;
        this.internalInfo = internalInfo;
    }
}
