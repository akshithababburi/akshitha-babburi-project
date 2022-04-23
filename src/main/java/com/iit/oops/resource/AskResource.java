package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Ask;
import com.iit.oops.service.AskService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bn/api")
@Produces(MediaType.APPLICATION_JSON)
public class AskResource {

    private final AskService askService;

    public AskResource(AskService askService) {
        this.askService = askService;
    }

    @POST
    @Path("/accounts/{uid}/asks")
    @Consumes(MediaType.APPLICATION_JSON)
    public Ask createAsk(@PathParam("uid") String uid, Ask ask) throws BuyNothingException {
        return askService.createAsk(ask, uid);
    }

    @GET
    @Path("/accounts/{uid}/asks/{aid}/deactivate")
    public Ask deactivateAsk(@PathParam("uid") String uid, @PathParam("aid") String aid) throws BuyNothingException {
        return askService.deactivateAsk(uid, aid);
    }

    @PUT
    @Path("/accounts/{uid}/asks/{aid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Ask updateAsk(@PathParam("uid") String uid,
                         @PathParam("aid") String aid, Ask ask) throws BuyNothingException {
        return askService.updateAsk(ask, uid, aid);
    }

    @DELETE
    @Path("/accounts/{uid}/asks/{aid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteAsk(@PathParam("uid") String uid,
                          @PathParam("aid") String aid) throws BuyNothingException {
        askService.deleteAsk(uid, aid);
    }

    @GET
    @Path("/accounts/{uid}/asks")
    public List<Ask> getAsksByUid(@QueryParam("is_active") boolean is_active,
                                  @PathParam("uid") String uid) throws BuyNothingException {
        return askService.getAsksByUid(uid, is_active);
    }

    @GET
    @Path("/asks/{aid}")
    public Ask getById(@PathParam("aid") String aid) throws BuyNothingException {
        return askService.getAskById(aid);
    }

    @GET
    @Path("/asks")
    public List<Ask> searchAsks(@QueryParam("key") String key,
                                @QueryParam("start_date") String start_date,
                                @QueryParam("end_date") String end_date) throws BuyNothingException {
        return askService.searchAsks(key, start_date, end_date);
    }

}
