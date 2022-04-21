package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.exception.UnAuthorizedException;
import com.iit.oops.model.Give;
import com.iit.oops.service.GiveService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
public class GiveResource {

    private final GiveService giveService;

    public GiveResource(GiveService giveService) {
        this.giveService = giveService;
    }

    @POST
    @Path("/accounts/{uid}/gives")
    @Consumes(MediaType.APPLICATION_JSON)
    public Give createGive(@PathParam("uid") String uid, Give give) throws BuyNothingException {
        return giveService.createGive(give, uid);
    }

    @GET
    @Path("/accounts/{uid}/gives/{gid}/deactivate")
    public Give deactivateGive(@PathParam("uid") String uid, @PathParam("gid") String gid) throws BuyNothingException, UnAuthorizedException {
        return giveService.deactivateGive(uid, gid);
    }

    @POST
    @Path("/accounts/{uid}/gives/{gid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Give updateGive(@PathParam("uid") String uid,
                           @PathParam("gid") String gid, Give give) throws UnAuthorizedException {
        return giveService.updateGive(give, uid, gid);
    }

    @DELETE
    @Path("/accounts/{uid}/gives/{gid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteGive(@PathParam("uid") String uid,
                           @PathParam("gid") String gid) throws UnAuthorizedException {
        giveService.deleteGive(uid, gid);
    }

    @GET
    @Path("/accounts/{uid}/gives")
    public List<Give> viewMyGives(@PathParam("uid") String uid) throws BuyNothingException {
        return giveService.viewMyGives(uid);
    }

    @GET
    @Path("/gives")
    public List<Give> getGives(@QueryParam("keyword") String keyword,
                               @QueryParam("start_date") String start_date,
                               @QueryParam("end_date") String end_date) throws BuyNothingException {
        return giveService.getAllGives(keyword, start_date, end_date);
    }

    @GET
    @Path("/gives/{gid}")
    public Give getById(@PathParam("gid") String gid) throws BuyNothingException {
        return giveService.getGiveById(gid);
    }


}
