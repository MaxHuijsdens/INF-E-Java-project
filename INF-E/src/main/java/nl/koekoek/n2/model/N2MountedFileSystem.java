package nl.koekoek.n2.model;

/**
 * Class representing a mounted file system of a n2 host.
 * @author Tom
 *
 */
public class N2MountedFileSystem {

    private String mountPoint;
    private double diskSize;
    private String filesystemType;
    private double usage;

    /**
     * Default constructor.
     */
    public N2MountedFileSystem() {

    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public double getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(double diskSize) {
        this.diskSize = diskSize;
    }

    public String getFilesystemType() {
        return filesystemType;
    }

    public void setFilesystemType(String filesystemType) {
        this.filesystemType = filesystemType;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

}
