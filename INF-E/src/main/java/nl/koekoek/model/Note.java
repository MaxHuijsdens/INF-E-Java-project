package nl.koekoek.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@SuppressWarnings("serial")
@Entity
public class Note extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "server_id")
    private Server server;

    private String reporter;

    @Lob
    private String noteText;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime incidentDate;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime creationDate;

    /**
     * Default constructor.
     */
    public Note() {
    }

    /**
     * Custom Contructor.
     * @param server the server
     * @param reporter the reporter
     * @param noteText the text
     * @param incidentDate the incident date
     */
    public Note(Server server, String reporter, String noteText, LocalDateTime incidentDate) {
        this.server = server;
        this.reporter = reporter;
        this.noteText = noteText;
        this.incidentDate = incidentDate;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public LocalDateTime getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(LocalDateTime incidentDate) {
        this.incidentDate = incidentDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
