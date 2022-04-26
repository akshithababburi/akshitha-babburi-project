package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Give;
import com.iit.oops.service.GiveService;
import com.iit.oops.util.ResponseUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/bn/api")
public class GiveResource {

    private final GiveService giveService;
    private final ResponseUtil responseUtil;

    public GiveResource(GiveService giveService, ResponseUtil responseUtil) {
        this.giveService = giveService;
        this.responseUtil = responseUtil;
    }

    @POST
    @Path("/accounts/{uid}/gives")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGive(@PathParam("uid") String uid, Give give) {
        try {
            Give giveFromRepo = giveService.createGive(give, uid);
            String location = "bn/api/accounts/" + giveFromRepo.getUid() + "/gives/" + giveFromRepo.getGid();
            return responseUtil.createSuccessResponse(giveFromRepo, Response.Status.CREATED, location);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/accounts/{uid}/gives/{gid}/deactivate")
    public Response deactivateGive(@PathParam("uid") String uid, @PathParam("gid") String gid) {
        try {
            Give giveFromRepo = giveService.deactivateGive(uid, gid);
            return responseUtil.createSuccessResponse(giveFromRepo, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @PUT
    @Path("/accounts/{uid}/gives/{gid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGive(@PathParam("uid") String uid,
                               @PathParam("gid") String gid, Give give) {
        try {
            Give giveFromRepo = giveService.updateGive(give, uid, gid);
            return responseUtil.createSuccessResponse(giveFromRepo, Response.Status.NO_CONTENT, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("/accounts/{uid}/gives/{gid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteGive(@PathParam("uid") String uid,
                               @PathParam("gid") String gid) {
        try {
            giveService.deleteGive(uid, gid);
            return responseUtil.createSuccessResponse(null, Response.Status.NO_CONTENT, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/accounts/{uid}/gives")
    public Response viewMyGives(@PathParam("uid") String uid) {
        try {
            List<Give> myGives = giveService.viewMyGives(uid);
            return responseUtil.createSuccessResponse(myGives, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/gives")
    public Response getGives(@QueryParam("key") String key,
                             @QueryParam("start_date") String start_date,
                             @QueryParam("end_date") String end_date) {
        try {
            List<Give> myGives = giveService.getAllGives(key, start_date, end_date);
            return responseUtil.createSuccessResponse(myGives, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Path("/gives/{gid}")
    public Response getById(@PathParam("gid") String gid) {
        try {
            Give give = giveService.getGiveById(gid);
            return responseUtil.createSuccessResponse(give, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }


}
