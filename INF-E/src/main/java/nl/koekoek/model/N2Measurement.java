package nl.koekoek.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.LocalDateTime;

/**
 * Model representing the n2 measurements.
 * @author Tom
 *
 */
@Entity
@DiscriminatorValue("n2")
public class N2Measurement extends Measurement {

    // persistent data
    private String status;
    private double cpuUsagePercent;
    private double diskUsagePercent;

    /**
     * Default constructor.
     */
    public N2Measurement() {

    }

    /**
     * Constructor with every parameter.
     * @param server the server of the measurement
     * @param measureDate the date its measured
     * @param status the status
     * @param uptime how long the server is up
     * @param load the load of a server
     * @param cpuUsagePercent the cpu usage in percentages
     * @param diskUsagePercent the disk usage in percentages
     * @param networkIn network in
     * @param networkOut network out
     * @param diskIO disk io
     * @param roundtripTime roundtrip time
     * @param ioWait io wait
     * @param freeRam free ram
     * @param freeSwap free swap
     * @param processesRunning ammount of processes running
     */
    public N2Measurement(Server server, LocalDateTime measureDate, String status, String uptime, double load, double cpuUsagePercent,
            double diskUsagePercent, int networkIn, int networkOut, int diskIO, double roundtripTime, int ioWait, double freeRam, double freeSwap,
            int processesRunning) {
        super();
        this.setServer(server);
        this.setTimeOfMeasurement(measureDate);
        this.status = status;
        this.setUptime(uptime);
        this.setLoad(load);
        this.cpuUsagePercent = cpuUsagePercent;
        this.diskUsagePercent = diskUsagePercent;
        this.setNetworkIn(networkIn);
        this.setNetworkOut(networkOut);
        this.setDiskIo(diskIO);
        this.setRoundtripTime(roundtripTime);
        this.setIoWait(ioWait);
        this.setProcessesRunning(processesRunning);
        this.setFreeRam(freeRam);
        this.setFreeSwap(freeSwap);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCpuUsagePercent() {
        return cpuUsagePercent;
    }

    public void setCpuUsagePercent(double cpuUsagePercent) {
        this.cpuUsagePercent = cpuUsagePercent;
    }

    public double getDiskUsagePercent() {
        return diskUsagePercent;
    }

    public void setDiskUsagePercent(double diskUsagePercent) {
        this.diskUsagePercent = diskUsagePercent;
    }

}
