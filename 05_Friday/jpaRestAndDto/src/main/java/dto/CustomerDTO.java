package dto;

import entities.BankCustomer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private Long customerId;
    private String fullName;
    private String accountNumber;
    private double balance;

    public CustomerDTO (BankCustomer bankCustomer) {
        this.customerId = bankCustomer.getId();
        this.fullName = bankCustomer.getFirstName() + " " + bankCustomer.getLastName();
        this.accountNumber = bankCustomer.getAccountNumber();
        this.balance = bankCustomer.getBalance();
    }
}
