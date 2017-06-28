package nl.koekoek.service;

import java.util.List;

import nl.koekoek.model.Note;
import nl.koekoek.repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Noteservice that talks to the repository
 * @author bastiaan
 *
 */
@Service
@Transactional
public class NoteService {

    private NoteRepository noteRepository;

    /**
     * Default contructor.
     */
    public NoteService() {

    }

    /**
     * Contructor.
     * @param noteRepository object
     */
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Saves a note (new or existing).
     * @param note note to safe
     * @return saved entity
     */
    public Note saveNote(Note note) {
        return this.noteRepository.save(note);
    }

    /**
     * deletes the note with the given id.
     * @param note to be deleted
     */
    public void deleteNote(Note note) {
        this.noteRepository.delete(note);
    };

    /**
     * Finds all notes for a server.
     * @param serverId the server id
     * @return all notes for a server
     */
    public List<Note> findNotesByServer(long serverId) {
        return this.noteRepository.findByServerId(serverId);
    };

    /**
     * Finds a note by its id.
     * @param id of the note
     * @return note found
     */
    public Note findById(long id) {
        return this.noteRepository.findOne(id);
    };

    /**
     * Gets all notes.
     * @return a list with found notes
     */
    public List<Note> getAllNotes() {
        return this.noteRepository.findAll();
    };
}
