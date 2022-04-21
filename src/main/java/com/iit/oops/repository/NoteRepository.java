package com.iit.oops.repository;

import com.iit.oops.model.Note;

import java.util.*;

public class NoteRepository {

    static Map<String, Note> noteMap = new HashMap<>();

    static Map<String, List<String>> noteAndRelatedNotesMap = new HashMap<>();

    public Optional<Note> createNote(Note note) {
        if (note.getTo_type().equalsIgnoreCase("note")) {
            List<String> noteAndRelatedNotesList;

            if (noteAndRelatedNotesMap.containsKey(note.getTo_id()))
                noteAndRelatedNotesList = noteAndRelatedNotesMap.get(note.getTo_id());
            else
                noteAndRelatedNotesList = new ArrayList<>();

            noteAndRelatedNotesList.add(note.getNid());
            noteAndRelatedNotesMap.put(note.getTo_id(), noteAndRelatedNotesList);
        }
        noteMap.put((note.getNid()), note);
        return Optional.of(note);
    }

    public Optional<Note> updateNote(Note note, String nid, String type, String typeId) {
        return Optional.of(noteMap.put(nid, note));
    }

    public void deleteNote(String nid) {
        List<String> notesToBeDeleted;

        if (noteAndRelatedNotesMap.containsKey(nid))
            notesToBeDeleted = noteAndRelatedNotesMap.get(nid);
        else
            notesToBeDeleted = new ArrayList<>();
        notesToBeDeleted.add(nid);

        for (String noteTobeDeleted : notesToBeDeleted) {
            noteMap.remove(noteTobeDeleted);
        }
    }

    public Optional<Note> getNoteByNid(String nid) {
        if (null != noteMap.get(nid))
            return Optional.of(noteMap.get(nid));
        else
            return Optional.of(new Note());
    }
}
