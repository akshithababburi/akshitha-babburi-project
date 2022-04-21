package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.exception.UnAuthorizedException;
import com.iit.oops.model.Thanks;
import com.iit.oops.service.ThanksService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
public class ThanksResource {
    private final ThanksService thanksService;

    public ThanksResource(ThanksService thanksService) {
        this.thanksService = thanksService;
    }


    @POST
    @Path("/accounts/{uid}/thanks")
    @Consumes(MediaType.APPLICATION_JSON)
    public Thanks createThanks(@PathParam("uid") String uid, Thanks thanks) throws BuyNothingException {
        return thanksService.createThanks(thanks, uid);
    }


    @POST
    @Path("/accounts/{uid}/thanks/{tid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Thanks updateThanks(@PathParam("uid") String uid,
                               @PathParam("tid") String tid,
                               Thanks thanks) throws UnAuthorizedException, BuyNothingException {
        return thanksService.updateThanks(thanks, uid, tid);
    }

    @GET
    @Path("/accounts/{uid}/thanks")
    public List<Thanks> getThanksCreatedByUid(@PathParam("uid") String uid) throws BuyNothingException {
        return thanksService.getAllThanksCreatedByUid(uid);
    }

    @GET
    @Path("/thanks")
    public List<Thanks> getThanks(@QueryParam("keyword") String keyword) throws BuyNothingException {
        return thanksService.getAllThanks(keyword);
    }

    @GET
    @Path("/thanks/{tid}")
    public Thanks getByTid(@PathParam("tid") String tid) throws BuyNothingException {
        return thanksService.getThanksByTid(tid);
    }

    @GET
    @Path("/thanks/received/{uid}")
    public List<Thanks> getThanksReceivedByUid(@PathParam("uid") String uid) throws BuyNothingException {
        return thanksService.getAllThanksReceivedByUid(uid);
    }
}
