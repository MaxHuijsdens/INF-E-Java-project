package nl.koekoek.model;

import java.util.Map;

/**
 * ServerVersionJson object. Use to put the current versions with a server and show 
 * them to the user at the frontend.
 * @author bastiaan
 *
 */
public class ServerVersionJson {

    private long serverId;
    private String hostName;
    private String supportName;

    private Map<String, String> versions;

    /**
     * Default contructor.
     */
    public ServerVersionJson() {

    }

    /**
     * Constructor with all parameters.
     * @param serverId the serverId
     * @param hostName the hostname of a server
     */
    public ServerVersionJson(long serverId, String hostName) {
        this.serverId = serverId;
        this.hostName = hostName;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Map<String, String> getVersions() {
        return versions;
    }

    public void setVersions(Map<String, String> versions) {
        this.versions = versions;
    }

    /**
     * @return the supportName
     */
    public String getSupportName() {
        return supportName;
    }

    /**
     * @param supportName the supportName to set
     */
    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

}
