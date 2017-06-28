package nl.koekoek.n2.model;

/**
 * Class that represents a process on a n2 host.
 * @author Tom
 *
 */
public class N2Process {

    private String name;
    private String user;
    private double memoryUsage;
    private double cpuUsage;

    /**
     * Default constructor.
     */
    public N2Process() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    @Override
    public String toString() {
        return "name = " + this.name + ", user = " + this.user + ", memory usage = " + this.memoryUsage
                + ", cpu usage = " + this.cpuUsage;
    }
}
