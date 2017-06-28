package nl.koekoek.n2.model;

/**
 * Holds the averages at certain recordtimes for a host.
 * @author Tom
 *
 */
public class N2HostDetailAverage {

    private String recordtime;
    private double cpuAvg;
    private double netinAvg;
    private double netoutAvg;
    private double iowaitAvg;
    private double loadAvg;
    private double diskioAvg;
    private double ramAvg;
    private double swapAvg;
    private double rttAvg;
    private double nprocAvg;

    /**
     * Default constructor.
     */
    public N2HostDetailAverage() {

    }

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }

    public double getCpuAvg() {
        return cpuAvg;
    }

    public void setCpuAvg(double cpuAvg) {
        this.cpuAvg = cpuAvg;
    }

    public double getNetinAvg() {
        return netinAvg;
    }

    public void setNetinAvg(double netinAvg) {
        this.netinAvg = netinAvg;
    }

    public double getNetoutAvg() {
        return netoutAvg;
    }

    public void setNetoutAvg(double netoutAvg) {
        this.netoutAvg = netoutAvg;
    }

    public double getIowaitAvg() {
        return iowaitAvg;
    }

    public void setIowaitAvg(double iowaitAvg) {
        this.iowaitAvg = iowaitAvg;
    }

    public double getLoadAvg() {
        return loadAvg;
    }

    public void setLoadAvg(double loadAvg) {
        this.loadAvg = loadAvg;
    }

    public double getDiskioAvg() {
        return diskioAvg;
    }

    public void setDiskioAvg(double diskioAvg) {
        this.diskioAvg = diskioAvg;
    }

    public double getRamAvg() {
        return ramAvg;
    }

    public void setRamAvg(double ramAvg) {
        this.ramAvg = ramAvg;
    }

    public double getSwapAvg() {
        return swapAvg;
    }

    public void setSwapAvg(double swapAvg) {
        this.swapAvg = swapAvg;
    }

    public double getRttAvg() {
        return rttAvg;
    }

    public void setRttAvg(double rttAvg) {
        this.rttAvg = rttAvg;
    }

    public double getNprocAvg() {
        return nprocAvg;
    }

    public void setNprocAvg(double nprocAvg) {
        this.nprocAvg = nprocAvg;
    }

}
