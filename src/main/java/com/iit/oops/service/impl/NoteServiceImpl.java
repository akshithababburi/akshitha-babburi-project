package com.iit.oops.service.impl;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Note;
import com.iit.oops.repository.NoteRepository;
import com.iit.oops.service.NoteService;

import java.util.List;
import java.util.Optional;

public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note createNote(Note note) throws BuyNothingException {
        Optional<Note> noteFromDB = noteRepository.createNote(note);
        if (noteFromDB.isPresent())
            return noteFromDB.get();
        else
            throw new BuyNothingException("404", "Something went wrong");
    }

    @Override
    public Note updateNote(Note note, String nid) throws BuyNothingException {
        Optional<Note> noteFromDB = noteRepository.updateNote(note, nid);
        if (noteFromDB.isPresent())
            return noteFromDB.get();
        else
            throw new BuyNothingException("404", "Something went wrong");
    }

    @Override
    public void deleteNote(String nid) {
        noteRepository.deleteNote(nid);
    }

    @Override
    public Note getNoteByNid(String nid) throws BuyNothingException {
        Optional<Note> noteFromDB = noteRepository.getNoteByNid(nid);
        if (noteFromDB.isPresent())
            return noteFromDB.get();
        else
            throw new BuyNothingException("404", "Something went wrong");
    }

    @Override
    public List<Note> searchNotes(String keyword) throws BuyNothingException {
        Optional<List<Note>> noteFromDB = noteRepository.searchNotes(keyword);
        if (noteFromDB.isPresent()) {
            return noteFromDB.get();
        }
        throw new BuyNothingException("404", "Something went wrong");
    }
}
