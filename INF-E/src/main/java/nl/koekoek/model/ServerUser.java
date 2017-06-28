package nl.koekoek.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ServerUser object.
 * @author bastiaan
 *
 */
@SuppressWarnings("serial")
@Entity
public class ServerUser extends AbstractEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "user_id")
    private User user;

    private boolean sudo;

    /**
     * Default contructor.
     */
    public ServerUser() {
        super();
    }

    /**
     * Constructor with server and user defined.
     * @param server the server
     * @param user the user
     */
    public ServerUser(Server server, User user) {
        super();
        this.server = server;
        this.user = user;
    }

    public boolean isSudo() {
        return sudo;
    }

    public void setSudo(boolean sudo) {
        this.sudo = sudo;
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

}
