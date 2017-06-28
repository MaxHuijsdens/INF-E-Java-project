package nl.koekoek.model;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 * A abstract class for the measurement. They share all these parameters
 * @author bastiaan
 *
 */
@Entity
@Table(name = "measurement")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "data_source")
public class Measurement extends AbstractEntity {

    // foreign key to server
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    private Server server;

    // date
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime timeOfMeasurement;

    private double load;

    private double networkIn;
    private double networkOut;

    private double diskIo;
    private double roundtripTime;
    private double ioWait;
    private double freeRam;
    private double freeSwap;

    private int processesRunning;
    private String uptime;
    @Column(name = "data_source", insertable = false, updatable = false)
    private String dataSource;

    /**
     * Default constructor.
     */
    public Measurement() {
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

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public double getNetworkIn() {
        return networkIn;
    }

    public void setNetworkIn(double networkIn) {
        this.networkIn = networkIn;
    }

    public double getNetworkOut() {
        return networkOut;
    }

    public void setNetworkOut(double networkOut) {
        this.networkOut = networkOut;
    }

    public int getProcessesRunning() {
        return processesRunning;
    }

    public void setProcessesRunning(int processesRunning) {
        this.processesRunning = processesRunning;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public double getDiskIo() {
        return diskIo;
    }

    public void setDiskIo(double diskIo) {
        this.diskIo = diskIo;
    }

    public double getRoundtripTime() {
        return roundtripTime;
    }

    public void setRoundtripTime(double roundtripTime) {
        this.roundtripTime = roundtripTime;
    }

    public double getIoWait() {
        return ioWait;
    }

    public void setIoWait(double ioWait) {
        this.ioWait = ioWait;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public double getFreeRam() {
        return freeRam;
    }

    public void setFreeRam(double freeRam) {
        this.freeRam = freeRam;
    }

    public double getFreeSwap() {
        return freeSwap;
    }

    public void setFreeSwap(double freeSwap) {
        this.freeSwap = freeSwap;
    }

}
