package nl.koekoek.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
public class Iptable extends AbstractEntity {

    private String tableRule;
    private boolean known;

    @JsonIgnore
    @OneToMany(mappedBy = "iptable")
    private Set<ServerIptable> serverIptables = new HashSet<ServerIptable>();

    /**
     * Default contructor.
     */
    public Iptable() {

    }

    /**
     * Custom contructor.
     * @param tableRule name
     */
    public Iptable(String tableRule) {
        this.tableRule = tableRule;
    }

    public String getTableRule() {
        return tableRule;
    }

    public void setTableRule(String tableRule) {
        this.tableRule = tableRule;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public Set<ServerIptable> getServerIptables() {
        return serverIptables;
    }

    public void setServerIptables(Set<ServerIptable> serverIptables) {
        this.serverIptables = serverIptables;
    }

}
