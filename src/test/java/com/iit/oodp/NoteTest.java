package com.iit.oodp;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Note;
import com.iit.oops.repository.NoteRepository;
import com.iit.oops.service.NoteService;
import com.iit.oops.service.impl.NoteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class NoteTest {

    private NoteService noteService;
    private NoteRepository noteRepository;

    @Before
    public void init() {
        noteRepository = new NoteRepository();
        noteService = new NoteServiceImpl(noteRepository);
    }


    @Test
    public void testCreateNote() throws BuyNothingException {

        Note note = new Note("1", "1", "give", "1", "1", "note",
                LocalDate.now());
        Assert.assertEquals(noteService.createNote(note).getNid(), "1");
    }

    @Test
    public void testUpdateNote() throws BuyNothingException {

        Note note = new Note("1", "1", "give", "1", "1", "note",
                LocalDate.now());
        noteService.createNote(note);
        note.setDescription("thanks");
        Assert.assertEquals(noteService.updateNote(note, "1", "give", "1").getDescription(), "thanks");
    }


    @Test
    public void testDeleteNote() throws BuyNothingException {

        Note note = new Note("1", "1", "give", "1", "1", "note",
                LocalDate.now());
        noteService.createNote(note);
        noteService.deleteNote("1");
        Assert.assertEquals(noteService.getNoteByNid("1").getDescription(), null);
    }


    @Test
    public void testGetNoteByNid() throws BuyNothingException {

        Note note = new Note("1", "1", "give", "1", "1", "note",
                LocalDate.now());
        noteService.createNote(note);
        Assert.assertEquals(noteService.getNoteByNid("1").getDescription(), "note");
    }


//
//    Note getNoteByNid(String nid) throws BuyNothingException;
}
