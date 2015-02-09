package rewardsonline.rest;

import java.math.BigDecimal;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import rewardsonline.accounts.Account;
import rewardsonline.accounts.Transaction;



@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AccountResource extends ResourceSupport {
    String number;
    String type;
    String creditCardNumber;
    BigDecimal balance;
    @JsonUnwrapped
    Resources<Transaction> transactions;
   
    public AccountResource() { this(null); }
    public AccountResource(Account account) {
        //super(account);
        this.number = account.getNumber();
        this.type = account.getType();
        this.creditCardNumber = account.getCreditCardNumber();
        BigDecimal balance = account.getBalance().getValue();
        this.balance = balance;
        this.transactions = new Resources<Transaction>(account.getTransactions());
    }
}
/*
class AccountResourceAssembler extends ResourceAssemblerSupport<Account, AccountResource> {

    public AccountResourceAssembler() {
        super(ResourceController.class, AccountResource.class);
    }

    //@Override
    public AccountResource toResource(Account account) {
        AccountResource resource = super.instantiateResource(account);
                                    //createResource(account);        
        resource.number = account.getNumber();
        resource.type = account.getType();
        resource.creditCardNumber = account.getCreditCardNumber();
        BigDecimal balance = account.getBalance().getValue();
        resource.balance = balance;
        return resource;
    }
}
*/
