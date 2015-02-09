package rewardsonline.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import rewardsonline.accounts.*;

/**
 * Manages access to member account information.
 */
@Service("accountService")
@Transactional(propagation=Propagation.REQUIRED, readOnly=true) 
//               isolation=Isolation.SERIALIZABLE, timeout=5000)
public class AccountService {
    AccountManager accountManager;

    
    @Autowired
    public AccountService(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Find the customer with the provided account number.
     * 
     * @param owner
     *            Name of the owner of the accounts.
     * @return The customer or <code>null</code> if no customer exists with that
     *         name.
     */
    public Customer findCustomer(String owner) { 
        return accountManager.findCustomer(owner);
    }

    /**
     * Return a listing of all accounts for a given customer.
     * 
     * @param owner
     *            Name of the owner of the accounts.
     * @return the account listing
     */
    public List<Account> findAllAccounts(String owner) { 
        return accountManager.findAllAccounts(owner);
    }

    /**
     * Find the account with the provided account number.
     * 
     * @param number
     *            The account number
     * @return The account
     * @throws AccountNotFoundException
     *             If no such account exists.
     */
    public Account findAccount(String number) { 
        return accountManager.findAccount(number);
    }

}
