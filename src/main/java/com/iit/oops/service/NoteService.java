package com.iit.oops.service;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Note;

import java.util.List;

public interface NoteService {

    Note createNote(Note note) throws BuyNothingException;

    Note updateNote(Note note, String nid) throws BuyNothingException;

    void deleteNote(String nid);

    Note getNoteByNid(String nid) throws BuyNothingException;

    List<Note> searchNotes(String key) throws BuyNothingException;

}
