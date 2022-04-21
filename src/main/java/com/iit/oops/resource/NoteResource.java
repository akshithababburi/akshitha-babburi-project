package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Note;
import com.iit.oops.service.NoteService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public class NoteResource {

    private final NoteService noteService;

    public NoteResource(NoteService noteService) {
        this.noteService = noteService;
    }


    @POST
    @Path("/notes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Note createNote(Note note) throws BuyNothingException {
        return noteService.createNote(note);
    }

    @PUT
    @Path("/accounts/{uid}/{type}/{typeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Note updateNote(Note note, @PathParam("uid") String uid,
                           @PathParam("type") String type,
                           @PathParam("typeId") String typeId) throws BuyNothingException {
        return noteService.updateNote(note, uid, type, typeId);
    }


    @DELETE
    @Path("/{nid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteNote(@PathParam("nid") String nid) throws BuyNothingException {
        noteService.deleteNote(nid);
    }


    @GET
    @Path("/{nid}")
    public Note getNoteByNid(@PathParam("nid") String nid) throws BuyNothingException {
        return noteService.getNoteByNid(nid);
    }

}
