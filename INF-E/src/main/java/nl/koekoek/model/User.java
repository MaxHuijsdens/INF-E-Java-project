package nl.koekoek.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User object.
 * @author bastiaan
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "login_user")
public class User extends AbstractEntity {

    private String name;
    private boolean known;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<ServerUser> serverUsers = new HashSet<ServerUser>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<ServerUserChange> serverUserChanges = new HashSet<ServerUserChange>();

    /**
     * Default contructor.
     */
    public User() {
        super();
    }

    /**
     * Contructor with the name already given.
     * @param name the name of the user
     */
    public User(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ServerUser> getServerUsers() {
        return serverUsers;
    }

    public void setServerUsers(Set<ServerUser> serverUsers) {
        this.serverUsers = serverUsers;
    }

    public Set<ServerUserChange> getServerUserChanges() {
        return serverUserChanges;
    }

    public void setServerUserChanges(Set<ServerUserChange> serverUserChanges) {
        this.serverUserChanges = serverUserChanges;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

}
