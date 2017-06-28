package nl.koekoek.controller;

import java.util.List;

import nl.koekoek.controller.model.NoteJsonModel;
import nl.koekoek.model.Note;
import nl.koekoek.service.NoteService;
import nl.koekoek.support.RestResult;

import org.jarbframework.constraint.violation.UniqueKeyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for Notes.
 * @author Bastiaan
 */
@Controller
@RequestMapping(value = "/note")
public class NoteController {

    private final NoteService noteService;

    /**
     * Contructor.
     * @param noteService object
     */
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
    * saves a note in the database.
    * @param noteProcessor represents the json for a note
    * @return restresult of the saved note
    */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Note> create(@RequestBody NoteJsonModel noteProcessor) {
        Note note = noteProcessor.buildNew();
        RestResult<Note> restResult = createOrEdit(note);
        if (restResult.isSuccess()) {
            noteService.saveNote(note);
        }
        return restResult;
    }

    /**
     * gets all Notes. 
     * @return all notes 
     */
    @RequestMapping(value = "/noteList", method = RequestMethod.GET)
    @ResponseBody
    public List<Note> getNoteList() {
        return noteService.getAllNotes();
    }

    /**
     * gets all Notes for a server.
     * @return all notes for a server
     */
    @RequestMapping(value = "/noteListByServer/{serverId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Note> getNoteListByServer(@PathVariable long serverId) {
        return noteService.findNotesByServer(serverId);
    }

    /**
     * Updates a Note.
     * @param noteProcessor represents the json for a note
     * @param note the note to be updated
     * @return Restresult of the note
     */
    @RequestMapping(value = "/{note}", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Note> edit(@RequestBody NoteJsonModel noteProcessor, @PathVariable Note note) {
        return createOrEdit(noteProcessor.mergeInto(note));
    }

    /**
     * Deletes a note.
     * @param noteId id of the note to be deleted
     */
    @RequestMapping(value = "/delete/{noteId}", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult<Note> delete(@PathVariable long noteId) {
        try {
            Note note = noteService.findById(noteId);
            noteService.deleteNote(note);
            return RestResult.success(note);
        } catch (DataIntegrityViolationException d) {
            return RestResult.error("Note nog gekoppeld aan een server.");
        }
    }

    /**
     * Checks for certain errors when saving data.
     * @param note to be saved
     * @return error or success
     */
    private RestResult<Note> createOrEdit(Note note) {
        try {
            return RestResult.success(noteService.saveNote(note));
        } catch (UniqueKeyViolationException e) {
            return RestResult.error("Note bestaat al");
        } catch (DataIntegrityViolationException d) {
            return RestResult.error("Note bestaat al");
        }
    }
}
