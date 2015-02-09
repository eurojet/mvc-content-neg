package rewardsonline.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import rewardsonline.accounts.Account;
import rewardsonline.accounts.Customer;



@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CustomerResource extends ResourceSupport {
    String number;
    String name;
    String username;
    Date dateOfBirth;
    String email;
    boolean receiveNewsletter;
    boolean receiveMonthlyEmailUpdate;
    @JsonUnwrapped
    Resources<Resource> accounts;
        
    public CustomerResource(Customer customer) {
        number = customer.getNumber();
        name = customer.getName();
        username = customer.getUsername();
        dateOfBirth = customer.getDateOfBirth();
        email = customer.getEmail();
        receiveNewsletter = customer.isReceiveNewsletter();
        receiveMonthlyEmailUpdate = customer.isReceiveMonthlyEmailUpdate();
        
        //this.accounts = new Resources(customer.getAccounts()); 
        //new Resources(new AccountResourceAssembler().toResources(customer.getAccounts()));
        List<Resource> reslist = new ArrayList<Resource>();
        String str;
        for (Account ac : customer.getAccounts()) {
            Resource res = new Resource<Account>(ac);
            str = ac.getNumber();
            res.add(new Link( "/rest/user/"+customer.getUsername()+
                    "/account/"+str, str ));
            reslist.add( res );
        }
        Resources<Resource> resources = new Resources<Resource>(reslist);
//resources.add(new Link( "/rest/user/"+customer.getUsername()+"/account/{number}", "account" ));
        this.accounts = resources;
    }
    
    public String toString() {
        return "CustomerResource [number = '" + number + "', name = '" + name
                                + "', user-id = '" + username + "'] "+super.toString()+" - Accounts = " + accounts;
    }
}
/*
class CustomerResourceAssembler extends ResourceAssemblerSupport<Customer, CustomerResource> {

    public CustomerResourceAssembler() {
        this(ResourceController.class);
    }

    public CustomerResourceAssembler(Class<?> controllerType) {
        super(controllerType, CustomerResource.class);
    }

    //@Override
    public CustomerResource toResource(Customer customer) {
        CustomerResource resource = super.instantiateResource(customer);
        resource.number= customer.getNumber();
        resource.name= customer.getName();
        resource.username= customer.getUsername();
        resource.dateOfBirth = customer.getDateOfBirth();
        resource.email = customer.getEmail();
        resource.receiveNewsletter = customer.isReceiveNewsletter();
        resource.receiveMonthlyEmailUpdate = customer.isReceiveMonthlyEmailUpdate();
        return resource;
    }
}
*/
