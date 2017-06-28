package nl.koekoek.controller.model;

import nl.koekoek.model.Note;
import nl.koekoek.model.Server;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteJsonModel implements JsonModel<Note> {

    private Server server;
    private String reporter;
    private String noteText;
    private String incidentDate;

    @Override
    public Note buildNew() {
        Note note = new Note();
        note.setServer(server);
        note.setReporter(reporter);
        note.setNoteText(noteText);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd-HHmm");
        LocalDateTime incidentTime = formatter.parseLocalDateTime(this.incidentDate);
        note.setIncidentDate(incidentTime);
        LocalDateTime creationDate = formatter.parseLocalDateTime(new DateTime().toString("yyyyMMdd-HHmm"));
        note.setCreationDate(creationDate);
        return note;
    }

    @Override
    public Note mergeInto(Note note) {
        note.setReporter(reporter);
        note.setNoteText(noteText);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd-HHmm");
        LocalDateTime incidentTime = formatter.parseLocalDateTime(this.incidentDate);
        note.setIncidentDate(incidentTime);
        return note;
    }

}
