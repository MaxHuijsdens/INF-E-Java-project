package nl.koekoek.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Version object.
 * @author Tom
 * 
 */
@SuppressWarnings("serial")
@Entity
public class Version extends AbstractEntity {

    private String name;
    private String versionNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "version")
    private Set<ServerVersion> serverVersions = new HashSet<ServerVersion>();

    /**
     * Default constructor.
     */
    public Version() {
        super();
    }

    /**
     * Custom contructor.
     * @param name the name of the version
     * @param versionNumber version number
     */
    public Version(String name, String versionNumber) {
        super();
        this.name = name;
        this.setVersionNumber(versionNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ServerVersion> getServerVersions() {
        return serverVersions;
    }

    public void setServerVersions(Set<ServerVersion> serverVersions) {
        this.serverVersions = serverVersions;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

}
