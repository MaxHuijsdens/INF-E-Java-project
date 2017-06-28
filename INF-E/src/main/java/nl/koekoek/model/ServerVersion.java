package nl.koekoek.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ServerVersion Object.
 * @author bastiaan
 *
 */
@SuppressWarnings("serial")
@Entity
public class ServerVersion extends AbstractEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "version_id")
    private Version version;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime timeOfMeasurement;

    /**
     * Default Contructor.
     */
    public ServerVersion() {
        super();
    }

    /**
     * Contructor.
     * @param server the version
     * @param version the version
     * @param timeOfMeasurement the time the version has changed
     */
    public ServerVersion(Server server, Version version, LocalDateTime timeOfMeasurement) {
        super();
        this.server = server;
        this.version = version;
        this.timeOfMeasurement = timeOfMeasurement;
    }

    public LocalDateTime getTimeOfMeasurement() {
        return timeOfMeasurement;
    }

    public void setTimeOfMeasurement(LocalDateTime timeOfMeasurement) {
        this.timeOfMeasurement = timeOfMeasurement;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

}
