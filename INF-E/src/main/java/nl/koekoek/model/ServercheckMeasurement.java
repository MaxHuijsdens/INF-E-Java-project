package nl.koekoek.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Measurement object
 * @author bastiaan
 *
 */
@Entity
@DiscriminatorValue("servcheck")
public class ServercheckMeasurement extends Measurement {

    private double cpuUsage;
    private double diskUsage;
    private double totalMemory;
    private double inodeUsage;

    /**
     * Default constructor.
     */
    public ServercheckMeasurement() {
        super();
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(double diskUsage) {
        this.diskUsage = diskUsage;
    }

    public double getInodeUsage() {
        return inodeUsage;
    }

    public void setInodeUsage(double inodeUsage) {
        this.inodeUsage = inodeUsage;
    }

    public double getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(double totalMemory) {
        this.totalMemory = totalMemory;
    }

}
