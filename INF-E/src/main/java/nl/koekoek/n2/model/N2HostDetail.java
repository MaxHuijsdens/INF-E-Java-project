package nl.koekoek.n2.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class that holds details about a n2 host at a certain datetime.
 * @author Tom
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class N2HostDetail {

    private String hostname;
    private String status;
    private int uptime;
    private String operatingSystem;
    private String hardwarePlatform;
    private double loadAverage;
    private double cpuUsage;
    private int numberOfProcesses;
    private int ioWaitPercent;
    private double totalRAM;
    private double freeRAM;
    private double freeSwapMemory;
    private int networkIn;
    private int networkOut;
    private int diskActivity;
    private double roundTripTime;
    private double packetLoss;
    private Map<String, N2ConsoleSession> n2ConsoleSessions;
    private String virtualHosts;
    private String virtualMachines;
    private String recordTime;
    private int numberOfRunningProcesses;
    private List<N2MountedFileSystem> mountedFilesystems;
    private String[] runningServices;
    private Map<Integer, N2Process> processList;
    private Map<Integer, N2NetworkPort> n2NetworkPorts;
    private String uptimeString;
    private String hostLabel;

    /**
     * Default constructor.
     */
    public N2HostDetail() {

    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUptime() {
        return uptime;
    }

    public void setUptime(int uptime) {
        this.uptime = uptime;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getHardwarePlatform() {
        return hardwarePlatform;
    }

    public void setHardwarePlatform(String hardwarePlatform) {
        this.hardwarePlatform = hardwarePlatform;
    }

    public double getLoadAverage() {
        return loadAverage;
    }

    public void setLoadAverage(double loadAverage) {
        this.loadAverage = loadAverage;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public int getNumberOfProcesses() {
        return numberOfProcesses;
    }

    public void setNumberOfProcesses(int numberOfProcesses) {
        this.numberOfProcesses = numberOfProcesses;
    }

    public int getIoWaitPercent() {
        return ioWaitPercent;
    }

    public void setIoWaitPercent(int ioWaitPercent) {
        this.ioWaitPercent = ioWaitPercent;
    }

    public double getTotalRAM() {
        return totalRAM;
    }

    public void setTotalRAM(double totalRAM) {
        this.totalRAM = totalRAM;
    }

    public double getFreeRAM() {
        return freeRAM;
    }

    public void setFreeRAM(double freeRAM) {
        this.freeRAM = freeRAM;
    }

    public double getFreeSwapMemory() {
        return freeSwapMemory;
    }

    public void setFreeSwapMemory(double freeSwapMemory) {
        this.freeSwapMemory = freeSwapMemory;
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

    public int getDiskActivity() {
        return diskActivity;
    }

    public void setDiskActivity(int diskActivity) {
        this.diskActivity = diskActivity;
    }

    public double getRoundTripTime() {
        return roundTripTime;
    }

    public void setRoundTripTime(double roundTripTime) {
        this.roundTripTime = roundTripTime;
    }

    public double getPacketLoss() {
        return packetLoss;
    }

    public void setPacketLoss(double packetLoss) {
        this.packetLoss = packetLoss;
    }

    public Map<String, N2ConsoleSession> getConsoleSessions() {
        return n2ConsoleSessions;
    }

    public void setConsoleSessions(Map<String, N2ConsoleSession> n2ConsoleSession) {
        this.n2ConsoleSessions = n2ConsoleSession;
    }

    public String getVirtualHosts() {
        return virtualHosts;
    }

    public void setVirtualHosts(String virtualHosts) {
        this.virtualHosts = virtualHosts;
    }

    public String getVirtualMachines() {
        return virtualMachines;
    }

    public void setVirtualMachines(String virtualMachines) {
        this.virtualMachines = virtualMachines;
    }

    public String getRecordTime() {
        return recordTime;
    }

    /**
     * Get the record time as a DateTime Object.
     * @return DateTime Object.
     */
    public DateTime getRecordTimeAsDateTime() {
        DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm");
        return dateStringFormat.parseDateTime(this.getRecordTime());
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public int getNumberOfRunningProcesses() {
        return numberOfRunningProcesses;
    }

    public void setNumberOfRunningProcesses(int numberOfRunningProcesses) {
        this.numberOfRunningProcesses = numberOfRunningProcesses;
    }

    public String[] getRunningServices() {
        return runningServices;
    }

    public void setRunningServices(String[] newRunningServices) {
        if (newRunningServices == null) {
            this.runningServices = new String[0];
        } else {
            this.runningServices = Arrays.copyOf(newRunningServices, newRunningServices.length);
        }
    }

    public Map<Integer, N2Process> getProcessList() {
        return processList;
    }

    public void setProcessList(Map<Integer, N2Process> processList) {
        this.processList = processList;
    }

    public Map<Integer, N2NetworkPort> getNetworkPorts() {
        return n2NetworkPorts;
    }

    public void setNetworkPorts(Map<Integer, N2NetworkPort> n2NetworkPort) {
        this.n2NetworkPorts = n2NetworkPort;
    }

    public String getUptimeString() {
        return uptimeString;
    }

    public void setUptimeString(String uptimeString) {
        this.uptimeString = uptimeString;
    }

    public String getHostLabel() {
        return hostLabel;
    }

    public void setHostLabel(String hostLabel) {
        this.hostLabel = hostLabel;
    }

    public List<N2MountedFileSystem> getMountedFilesystems() {
        return mountedFilesystems;
    }

    public void setMountedFilesystems(List<N2MountedFileSystem> mountedFilesystems) {
        this.mountedFilesystems = mountedFilesystems;
    }

    /**
     * Prints the class in a readable format in the console.
     */
    public void printDetails() {

        System.out.println("hostname = " + this.hostname);
        System.out.println("status = " + this.status);
        System.out.println("uptime = " + this.uptime);
        System.out.println("operating system = " + this.operatingSystem);
        System.out.println("hardware platform = " + this.hardwarePlatform);
        System.out.println("load average = " + this.loadAverage);
        System.out.println("cpu usage = " + this.cpuUsage);
        System.out.println("number of processes = " + this.numberOfProcesses);
        System.out.println("io wait percent = " + this.ioWaitPercent);
        System.out.println("total ram = " + this.totalRAM);
        System.out.println("free ram = " + this.freeRAM);
        System.out.println("free swap memory = " + this.freeSwapMemory);
        System.out.println("network in = " + this.networkIn);
        System.out.println("network out = " + this.networkOut);
        System.out.println("disk activity = " + this.diskActivity);
        System.out.println("round trip time = " + this.roundTripTime);
        System.out.println("packet loss = " + this.packetLoss);
        System.out.println("number of running processes = " + this.numberOfRunningProcesses);
        System.out.println("record time = " + this.getRecordTime());
        System.out.println("uptime as string = " + this.uptimeString);
        System.out.println("hostlabel = " + this.hostLabel);

        System.out.println("-------------- Console sessions ------------");
        if (null != this.n2ConsoleSessions) {
            for (N2ConsoleSession cs : this.n2ConsoleSessions.values()) {
                if (null != cs) {
                    System.out.println();
                    System.out.println("remote host = " + cs.getRemoteHost());
                    System.out.println("username = " + cs.getUserName());
                    System.out.println();
                }
            }
        }
        System.out.println("-------------- Mounted file systems ------------");
        if (null != this.mountedFilesystems) {
            for (N2MountedFileSystem mfs : this.mountedFilesystems) {
                if (null != mfs) {
                    System.out.println();
                    System.out.println("mount point = " + mfs.getMountPoint());
                    System.out.println("file system type = " + mfs.getFilesystemType());
                    System.out.println("disk size = " + mfs.getDiskSize());
                    System.out.println("in use = " + mfs.getUsage());
                    System.out.println();
                }
            }
        }
        System.out.println("-------------- processes ------------");
        if (null != this.processList) {
            System.out.println();
            for (Entry<Integer, N2Process> entry : this.processList.entrySet()) {
                System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            }
            System.out.println();
        }
        System.out.println("-------------- network ports ------------");
        if (null != this.n2NetworkPorts) {
            System.out.println();
            for (Entry<Integer, N2NetworkPort> entry : this.n2NetworkPorts.entrySet()) {
                System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            }
            System.out.println();
        }
        System.out.println("-------------- running services ------------");
        if (null != this.runningServices) {
            for (String s : this.runningServices) {
                System.out.println(s);
            }
            System.out.println();
        }
    }
}
