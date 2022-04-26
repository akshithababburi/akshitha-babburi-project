package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Thanks;
import com.iit.oops.service.ThanksService;
import com.iit.oops.util.ResponseUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/bn/api")
public class ThanksResource {
    private final ThanksService thanksService;
    private final ResponseUtil responseUtil;

    public ThanksResource(ThanksService thanksService, ResponseUtil responseUtil) {
        this.thanksService = thanksService;
        this.responseUtil = responseUtil;
    }


    @POST
    @Path("/accounts/{uid}/thanks")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createThanks(@PathParam("uid") String uid, Thanks thanks) {
        try {
            Thanks thanksFromRepo = thanksService.createThanks(thanks, uid);
            String location = "bn/api/accounts/" + thanks.getUid() + "/thanks/" + thanks.getTid();
            return responseUtil.createSuccessResponse(thanksFromRepo, Response.Status.CREATED, location);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }


    @PUT
    @Path("/accounts/{uid}/thanks/{tid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateThanks(@PathParam("uid") String uid,
                                 @PathParam("tid") String tid,
                                 Thanks thanks) {
        try {
            Thanks thanksFromRepo = thanksService.updateThanks(thanks, uid, tid);
            return responseUtil.createSuccessResponse(thanksFromRepo, Response.Status.NO_CONTENT, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/accounts/{uid}/thanks")
    public Response getThanksCreatedByUid(@PathParam("uid") String uid) {
        try {
            List<Thanks> thanksFromRepo = thanksService.getAllThanksCreatedByUid(uid);
            return responseUtil.createSuccessResponse(thanksFromRepo, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/thanks")
    public Response getThanks(@QueryParam("key") String key) {

        try {
            List<Thanks> thanksFromRepo = thanksService.getAllThanks(key);
            return responseUtil.createSuccessResponse(thanksFromRepo, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/thanks/{tid}")
    public Response getByTid(@PathParam("tid") String tid) {
        try {
            Thanks thanksFromRepo = thanksService.getThanksByTid(tid);
            return responseUtil.createSuccessResponse(thanksFromRepo, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/thanks/received/{uid}")
    public Response getThanksReceivedByUid(@PathParam("uid") String uid) {
        try {
            List<Thanks> thanksFromRepo = thanksService.getAllThanksReceivedByUid(uid);
            return responseUtil.createSuccessResponse(thanksFromRepo, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }
}
