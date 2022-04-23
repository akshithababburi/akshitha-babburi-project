package com.iit.oops.resource;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Note;
import com.iit.oops.service.NoteService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/bn/api")
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
    @Path("/notes/{nid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Note updateNote(Note note, @PathParam("nid") String nid) throws BuyNothingException {
        return noteService.updateNote(note, nid);
    }


    @DELETE
    @Path("notes/{nid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteNote(@PathParam("nid") String nid) throws BuyNothingException {
        noteService.deleteNote(nid);
    }


    @GET
    @Path("notes/{nid}")
    public Note getNoteByNid(@PathParam("nid") String nid) throws BuyNothingException {
        return noteService.getNoteByNid(nid);
    }


    @GET
    @Path("notes")
    public List<Note> searchNotes(@QueryParam("key") String key) throws BuyNothingException {
        return noteService.searchNotes(key);
    }

}
