package nl.koekoek.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * PersistentServerData Object.
 * @author Kevin van Leeuwen
 *
 */
@SuppressWarnings("serial")
@Entity
@JsonIgnoreProperties({ "new" })
public class PersistentServerData extends AbstractEntity {

    // foreign key to server
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    private Server server;

    // date
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime timeOfMeasurement;
    private String ufwStatus;
    private int packagesCanUpdated;
    private int packagesSecurity;
    private boolean restartNeeded;
    private int diskSize;
    private boolean scriptErrorOccured;

    /**
     * default constructor.
     */
    public PersistentServerData() {
        super();
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public LocalDateTime getTimeOfMeasurement() {
        return timeOfMeasurement;
    }

    public void setTimeOfMeasurement(LocalDateTime timeOfMeasurement) {
        this.timeOfMeasurement = timeOfMeasurement;
    }

    public int getPackagesCanUpdated() {
        return packagesCanUpdated;
    }

    public void setPackagesCanUpdated(int packagesCanUpdated) {
        this.packagesCanUpdated = packagesCanUpdated;
    }

    public int getPackagesSecurity() {
        return packagesSecurity;
    }

    public void setPackagesSecurity(int packagesSecurity) {
        this.packagesSecurity = packagesSecurity;
    }

    public boolean isRestartNeeded() {
        return restartNeeded;
    }

    public void setRestartNeeded(boolean restartNeeded) {
        this.restartNeeded = restartNeeded;
    }

    public int getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(int diskSize) {
        this.diskSize = diskSize;
    }

    public String getUfwStatus() {
        return ufwStatus;
    }

    public void setUfwStatus(String ufwStatus) {
        this.ufwStatus = ufwStatus;
    }

    public boolean isScriptErrorOccured() {
        return scriptErrorOccured;
    }

    public void setScriptErrorOccured(boolean scriptErrorOccured) {
        this.scriptErrorOccured = scriptErrorOccured;
    }

}
