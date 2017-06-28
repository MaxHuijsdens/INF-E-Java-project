package nl.koekoek.n2.model;

/**
 * Class containing some general information of a n2 host.
 * @author Tom
 *
 */
public class N2HostOverview {

    private String group;
    private String groupDescription;
    private String id;
    private String label;
    private String status;
    private String statusFlags;
    private double cpuUsage;
    private double loadAverage;
    private double roundTripTime;
    private int diskIO;
    private int networkIn;
    private int networkOut;

    /**
     * Default constructor.
     */
    public N2HostOverview() {

    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusFlags() {
        return statusFlags;
    }

    public void setStatusFlags(String statusFlags) {
        this.statusFlags = statusFlags;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getLoadAverage() {
        return loadAverage;
    }

    public void setLoadAverage(double loadAverage) {
        this.loadAverage = loadAverage;
    }

    public double getRoundTripTime() {
        return roundTripTime;
    }

    public void setRoundTripTime(double roundTripTime) {
        this.roundTripTime = roundTripTime;
    }

    public int getDiskIO() {
        return diskIO;
    }

    public void setDiskIO(int diskIO) {
        this.diskIO = diskIO;
    }

    public int getNetworkIn() {
        return networkIn;
    }

    public void setNetworkIn(int networkIn) {
        this.networkIn = networkIn;
    }

    public int getNetworkOut() {
        return networkOut;
    }

    public void setNetworkOut(int networkOut) {
        this.networkOut = networkOut;
    }

    /**
     * Prints the class in a readable format in the console.
     */
    public void printDetails() {
        System.out.println("---------- Host overview ----------");
        System.out.println();
        System.out.println("group = " + this.group);
        System.out.println("group description = " + this.groupDescription);
        System.out.println("ip adress = " + this.id);
        System.out.println("label = " + this.label);
        System.out.println("status = " + this.status);
        System.out.println("status flags = " + this.statusFlags);
        System.out.println("cpu usage = " + this.cpuUsage);
        System.out.println("load average = " + this.loadAverage);
        System.out.println("round trip time = " + this.roundTripTime);
        System.out.println("disk io = " + this.diskIO);
        System.out.println("network in = " + this.networkIn);
        System.out.println("network out = " + this.networkOut);
        System.out.println();
    }
}
