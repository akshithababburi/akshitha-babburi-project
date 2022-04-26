package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Note;
import com.iit.oops.service.NoteService;
import com.iit.oops.util.ResponseUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/bn/api")
public class NoteResource {

    private final NoteService noteService;
    private final ResponseUtil responseUtil;

    public NoteResource(NoteService noteService, ResponseUtil responseUtil) {
        this.noteService = noteService;
        this.responseUtil = responseUtil;
    }


    @POST
    @Path("/notes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNote(Note note) {
        try {
            Note noteFromRepo = noteService.createNote(note);
            String location = "bn/api/notes/" + noteFromRepo.getNid();
            return responseUtil.createSuccessResponse(noteFromRepo, Response.Status.CREATED, location);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

    @PUT
    @Path("/notes/{nid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(Note note, @PathParam("nid") String nid) {
        try {
            Note noteFromRepo = noteService.updateNote(note, nid);
            return responseUtil.createSuccessResponse(noteFromRepo, Response.Status.NO_CONTENT, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }


    @DELETE
    @Path("notes/{nid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteNote(@PathParam("nid") String nid) {
        noteService.deleteNote(nid);
        return responseUtil.createSuccessResponse(null, Response.Status.NO_CONTENT, null);
    }


    @GET
    @Path("notes/{nid}")
    public Response getNoteByNid(@PathParam("nid") String nid) {
        try {
            Note noteFromRepo = noteService.getNoteByNid(nid);
            return responseUtil.createSuccessResponse(noteFromRepo, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }

    }


    @GET
    @Path("notes")
    public Response searchNotes(@QueryParam("key") String key) {
        try {
            List<Note> notesFromRepo = noteService.searchNotes(key);
            return responseUtil.createSuccessResponse(notesFromRepo, Response.Status.OK, null);
        } catch (BuyNothingException e) {
            return responseUtil.createErrorResponse(e, Response.Status.BAD_REQUEST);
        }
    }

}
