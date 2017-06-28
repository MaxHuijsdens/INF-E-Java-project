package nl.koekoek.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
public class ServerIptable extends AbstractEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "iptable_id")
    private Iptable iptable;

    /**
     * Default contructor.
     */
    public ServerIptable() {

    }

    /**
     * Custom contructor.
     * @param server for the entity
     * @param iptable for the entity
     */
    public ServerIptable(Server server, Iptable iptable) {
        this.server = server;
        this.iptable = iptable;
    }

    public Iptable getIptable() {
        return iptable;
    }

    public void setIptable(Iptable iptable) {
        this.iptable = iptable;
    }

}
