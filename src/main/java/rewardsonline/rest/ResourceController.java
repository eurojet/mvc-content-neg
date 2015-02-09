package rewardsonline.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rewardsonline.accounts.*;




@RestController
@RequestMapping("/rest")
public class ResourceController {
    
    private AccountService accountService;

    
    @Autowired
    public ResourceController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @RequestMapping("/user/{username}")
    public ResponseEntity getCustomer(Principal principal, 
                    @PathVariable("username") String username) {
        if (principal == null)
            throw new AccessDeniedException("Not authenitcated");
        if ( ! principal.getName().equals(username) ) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/rest/user/"+principal.getName());
            return new ResponseEntity<Void>(headers, HttpStatus.FOUND);
        }

        Customer customer = accountService.findCustomer(username);
        CustomerResource resource = new CustomerResource( customer ); 
        resource.add(linkTo(methodOn(ResourceController.class).getCustomer(
                    principal,  username)).withSelfRel() );
        resource.add(new Link(new UriTemplate(
                "/rest/user/"+username+"/account/{number}"), "account" ));

        return new ResponseEntity<CustomerResource>(resource, HttpStatus.OK);
    }
    
    @RequestMapping("/user/{username}/account")
    public ResponseEntity getAllAccounts(Principal principal, 
                    @PathVariable("username") String username) {
        if (principal == null)
            throw new AccessDeniedException("Not authenitcated");
        if ( ! principal.getName().equals(username) ) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/rest/user/"+principal.getName()+"/account");
            return new ResponseEntity<Void>(headers, HttpStatus.FOUND);
        }
        
        Resources resources = new Resources<Account>(
                    accountService.findAllAccounts(username) );
        resources.add(linkTo(methodOn(ResourceController.class).getAllAccounts(
                    principal,  username)).withSelfRel() );
        resources.add(new Link(new UriTemplate(
                    "/rest/user/"+username+"/account/{number}"), "account" ));
        
        return new ResponseEntity<Resources>(resources, HttpStatus.OK);
    }
    
    @RequestMapping("/user/{username}/account/{number}")
    public ResponseEntity getAccount(Principal principal, 
                    @PathVariable("username") String username, 
                    @PathVariable("number") String number) {
        if (principal == null)
            throw new AccessDeniedException("Not authenitcated");
        if ( ! principal.getName().equals(username) ) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/rest/user/"+principal.getName()+"/account/"+number);
            return new ResponseEntity<Void>(headers, HttpStatus.FOUND);
        }
        
        Account account = accountService.findAccount(number);
        AccountResource resource = new AccountResource(account);
        resource.add(linkTo(methodOn(ResourceController.class).getAccount(
                    principal, username, number)).withSelfRel() );
       
        return new ResponseEntity<AccountResource>(resource, HttpStatus.OK);
    }
}
