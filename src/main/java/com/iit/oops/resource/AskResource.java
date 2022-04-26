package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Ask;
import com.iit.oops.service.AskService;
import com.iit.oops.util.ResponseUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/bn/api")
@Produces(MediaType.APPLICATION_JSON)
public class AskResource {

    private final AskService askService;
    private final ResponseUtil responseUtil;

    public AskResource(AskService askService, ResponseUtil responseUtil) {
        this.askService = askService;
        this.responseUtil = responseUtil;
    }

    @POST
    @Path("/accounts/{uid}/asks")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAsk(@PathParam("uid") String uid, Ask ask) {
        try {
            Ask askCreated = askService.createAsk(ask, uid);
            String location = "bn/api/accounts/" + uid + "/asks/" + askCreated.getAid();

            return responseUtil.createSuccessResponse(askCreated, Response.Status.CREATED, location);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/accounts/{uid}/asks/{aid}/deactivate")
    public Response deactivateAsk(@PathParam("uid") String uid, @PathParam("aid") String aid) {
        try {
            Ask askCreated = askService.deactivateAsk(uid, aid);
            return responseUtil.createSuccessResponse(askCreated, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @PUT
    @Path("/accounts/{uid}/asks/{aid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAsk(@PathParam("uid") String uid,
                              @PathParam("aid") String aid, Ask ask) {
        try {
            Ask askCreated = askService.updateAsk(ask, uid, aid);
            return responseUtil.createSuccessResponse(askCreated, Response.Status.NO_CONTENT, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("/accounts/{uid}/asks/{aid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAsk(@PathParam("uid") String uid,
                              @PathParam("aid") String aid) {

        try {
            askService.deleteAsk(uid, aid);
            return responseUtil.createSuccessResponse(null, Response.Status.NO_CONTENT, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/accounts/{uid}/asks")
    public Response getAsksByUid(@QueryParam("is_active") boolean is_active,
                                 @PathParam("uid") String uid) {

        try {
            List<Ask> askList = askService.getAsksByUid(uid, is_active);
            return responseUtil.createSuccessResponse(askList, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/asks/{aid}")
    public Response getById(@PathParam("aid") String aid) {
        try {
            Ask askFromDb = askService.getAskById(aid);
            return responseUtil.createSuccessResponse(askFromDb, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/asks")
    public Response searchAsks(@QueryParam("key") String key,
                               @QueryParam("start_date") String start_date,
                               @QueryParam("end_date") String end_date) {

        try {
            List<Ask> askList = askService.searchAsks(key, start_date, end_date);
            return responseUtil.createSuccessResponse(askList, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

}
