package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Account;
import com.iit.oops.service.AccountService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bn/api")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @POST
    @Path("/accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    public Account createAccount(Account account) throws BuyNothingException {
        return accountService.createAccount(account);
    }

    @GET
    @Path("/accounts/{uid}/activate")
    public Account activateAccount(@PathParam("uid") String uid) throws BuyNothingException {
        return accountService.activateAccount(uid);
    }

    @PUT
    @Path("/accounts/{uid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Account updateAccount(@PathParam("uid") String uid, Account account) throws BuyNothingException {
        return accountService.updateAccount(uid, account);
    }

    @DELETE
    @Path("/accounts/{uid}")
    public void deleteAccount(@PathParam("uid") String uid) throws BuyNothingException {
        accountService.deleteAccount(uid);
    }

    @GET
    @Path("/accounts")
    public List<Account> getAccounts(@QueryParam("key") String key) throws BuyNothingException {
        return accountService.getAllAccounts(key);
    }

    @GET
    @Path("/accounts/{uid}")
    public Account getById(@PathParam("uid") String uid) throws BuyNothingException {
        return accountService.getAccountById(uid);
    }

}
