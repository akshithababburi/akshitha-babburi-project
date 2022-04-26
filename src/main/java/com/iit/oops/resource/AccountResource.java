package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Account;
import com.iit.oops.service.AccountService;
import com.iit.oops.util.ResponseUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/bn/api")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    private final AccountService accountService;
    private final ResponseUtil responseUtil;

    public AccountResource(AccountService accountService, ResponseUtil responseUtil) {
        this.accountService = accountService;
        this.responseUtil = responseUtil;
    }

    @POST
    @Path("/accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        try {
            Account accountCreated = accountService.createAccount(account);
            String location = "bn/api/accounts/" + accountCreated.getUid();
            return responseUtil.createSuccessResponse(accountCreated, Response.Status.CREATED, location);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/accounts/{uid}/activate")
    public Response activateAccount(@PathParam("uid") String uid) {
        try {
            Account accountCreated = accountService.activateAccount(uid);
            return responseUtil.createSuccessResponse(accountCreated, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @PUT
    @Path("/accounts/{uid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAccount(@PathParam("uid") String uid, Account account) {
        try {
            Account accountCreated = accountService.updateAccount(uid, account);
            return responseUtil.createSuccessResponse(accountCreated, Response.Status.NO_CONTENT, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("/accounts/{uid}")
    public Response deleteAccount(@PathParam("uid") String uid) {
        accountService.deleteAccount(uid);
        return responseUtil.createSuccessResponse(null, Response.Status.NO_CONTENT, null);
    }

    @GET
    @Path("/accounts")
    public Response getAccounts(@QueryParam("key") String key,
                                @QueryParam("start_date") String start_Date,
                                @QueryParam("end_date") String end_date) {
        try {
            List<Account> accountsCreated = accountService.getAllAccounts(key, start_Date, end_date);
            return responseUtil.createSuccessResponse(accountsCreated, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/accounts/{uid}")
    public Response getById(@PathParam("uid") String uid) {
        try {
            Account account = accountService.getAccountById(uid);
            return responseUtil.createSuccessResponse(account, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.NOT_FOUND);
        }
    }

}
