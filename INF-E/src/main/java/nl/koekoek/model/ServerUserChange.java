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
 * ServerUserChange object.
 * @author bastiaan
 *
 */
@SuppressWarnings("serial")
@Entity
public class ServerUserChange extends AbstractEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "user_id")
    private User user;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime timeOfMeasurement;

    private boolean joinOrLeft;

    /**
     * Default contructor.
     */
    public ServerUserChange() {
        super();
    }

    /**
     * Contructor with all parameters.
     * @param server the server
     * @param user the user
     * @param timeOfMeasurement the time the user has left or joined
     * @param joinOrleft true if joined false if left
     */
    public ServerUserChange(Server server, User user, LocalDateTime timeOfMeasurement, boolean joinOrleft) {
        super();
        this.server = server;
        this.user = user;
        this.timeOfMeasurement = timeOfMeasurement;
        this.joinOrLeft = joinOrleft;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimeOfMeasurement() {
        return timeOfMeasurement;
    }

    public void setTimeOfMeasurement(LocalDateTime timeOfMeasurement) {
        this.timeOfMeasurement = timeOfMeasurement;
    }

    public boolean isJoinOrLeft() {
        return joinOrLeft;
    }

    public void setJoinOrLeft(boolean joinOrLeft) {
        this.joinOrLeft = joinOrLeft;
    }

}
