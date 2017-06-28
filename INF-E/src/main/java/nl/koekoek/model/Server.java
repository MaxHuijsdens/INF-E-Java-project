package nl.koekoek.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Server Object.
 * @author Tom & Kevin van Leeuwen
 *
 */
@SuppressWarnings("serial")
@Entity
public class Server extends AbstractEntity {

    private String supportName;
    private String hostName;
    private String ipv4;
    private String ipv6;
    private String image;
    private String hostDns;
    private double cost;
    private String otap;
    private int totalRam;
    private int totalDiskSize;
    private int cores;
    private String confluencePageUrl;
    private boolean monitoredByServercheck;
    private boolean inHybernation;
    private String purpose;
    private boolean monitoredByNewrelic;
    private String remarks;

    // foreign key to customer
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "sla_id")
    private Sla sla;

    // foreign key to supplier
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "server")
    private Set<ServerUser> serverUsers = new HashSet<ServerUser>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "server")
    private Set<ServerUserChange> serverUserChanges = new HashSet<ServerUserChange>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "server")
    private Set<ServerVersion> serverVersions = new HashSet<ServerVersion>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "server")
    private Set<ServerIptable> serverIptables = new HashSet<ServerIptable>();

    /**
     * default constructor.
     */
    public Server() {
        super();
    }

    public String getSupportName() {
        return supportName;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getHostDns() {
        return hostDns;
    }

    public void setHostDns(String hostDns) {
        this.hostDns = hostDns;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getOtap() {
        return otap;
    }

    public void setOtap(String otap) {
        this.otap = otap;
    }

    public int getTotalRam() {
        return totalRam;
    }

    public void setTotalRam(int totalRam) {
        this.totalRam = totalRam;
    }

    public int getTotalDiskSize() {
        return totalDiskSize;
    }

    public void setTotalDiskSize(int totalDiskSize) {
        this.totalDiskSize = totalDiskSize;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public String getConfluencePageUrl() {
        return confluencePageUrl;
    }

    public void setConfluencePageUrl(String confluencePageUrl) {
        this.confluencePageUrl = confluencePageUrl;
    }

    public boolean isMonitoredByServercheck() {
        return monitoredByServercheck;
    }

    public void setMonitoredByServercheck(boolean controlledByServercheck) {
        this.monitoredByServercheck = controlledByServercheck;
    }

    public boolean isInHybernation() {
        return inHybernation;
    }

    public void setInHybernation(boolean inHybernation) {
        this.inHybernation = inHybernation;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public boolean isMonitoredByNewrelic() {
        return monitoredByNewrelic;
    }

    public void setMonitoredByNewrelic(boolean controlledByNewrelic) {
        this.monitoredByNewrelic = controlledByNewrelic;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setServerUsers(Set<ServerUser> serverUsers) {
        this.serverUsers = serverUsers;
    }

    public Set<ServerUser> getServerUsers() {
        return serverUsers;
    }

    public Set<ServerUserChange> getServerUserChanges() {
        return serverUserChanges;
    }

    public void setServerUserChanges(Set<ServerUserChange> serverUserChanges) {
        this.serverUserChanges = serverUserChanges;
    }

    public Set<ServerVersion> getServerVersions() {
        return serverVersions;
    }

    public void setServerVersions(Set<ServerVersion> serverVersions) {
        this.serverVersions = serverVersions;
    }

    public Sla getSla() {
        return sla;
    }

    public void setSla(Sla sla) {
        this.sla = sla;
    }

    public Set<ServerIptable> getServerIptables() {
        return serverIptables;
    }

    public void setServerIptables(Set<ServerIptable> serverIptables) {
        this.serverIptables = serverIptables;
    }

}
