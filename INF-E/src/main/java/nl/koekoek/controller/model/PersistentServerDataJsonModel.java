package nl.koekoek.controller.model;

import java.util.List;
import java.util.Map;

import nl.koekoek.model.PersistentServerData;
import nl.koekoek.model.Server;
import nl.koekoek.service.ServerService;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the http requestbody data of the serverMeasurement create/edit form.
 * @author Kevin van Leeuwen
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistentServerDataJsonModel {

    private Map<String, Object> serverData;
    private Map<String, String> versionsData;
    private List<String> usersArray;
    private List<String> sudoUsersArray;
    private List<String> iptablesRules;
    @JsonIgnore
    private Server server;
    @JsonIgnore
    private LocalDateTime measurementTime;

    /**
     * Creates a persistent server data.
     * @param serverService the serverService
     * @return the created persistent server data
     */
    public PersistentServerData createPersistentServerData(ServerService serverService) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd-HHmm");
        measurementTime = formatter.parseLocalDateTime((String) serverData.get("timeOfMeasurement"));
        server = serverService.getServerByHostName((String) serverData.get("hostName"));
        if (server == null) {
            server = new Server();
            server.setHostName((String) serverData.get("hostName"));
            server.setSupportName((String) serverData.get("hostName42"));
            serverService.addServer(server);
        } else {
            server.setSupportName((String) serverData.get("hostName42"));
            serverService.addServer(server);
        }
        PersistentServerData persistentServerData = new PersistentServerData();
        persistentServerData.setServer(server);
        persistentServerData.setTimeOfMeasurement(measurementTime);
        persistentServerData.setPackagesCanUpdated((Integer) serverData.get("packagesCanUpdated"));
        persistentServerData.setPackagesSecurity((Integer) serverData.get("packagesSecurity"));
        persistentServerData.setRestartNeeded((Boolean) serverData.get("restartNeeded"));
        persistentServerData.setDiskSize((Integer) serverData.get("diskSize"));
        persistentServerData.setUfwStatus((String) serverData.get("ufwStatus"));
        persistentServerData.setScriptErrorOccured((Boolean) serverData.get("scriptErrorOccured"));
        return persistentServerData;
    }

    public Map<String, String> getVersionsData() {
        return versionsData;
    }

    public void setVersionsData(Map<String, String> versionsData) {
        this.versionsData = versionsData;
    }

    public List<String> getUsersArray() {
        return usersArray;
    }

    public void setUsersArray(List<String> usersArray) {
        this.usersArray = usersArray;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public LocalDateTime getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(LocalDateTime measurementTime) {
        this.measurementTime = measurementTime;
    }

    public List<String> getSudoUsersArray() {
        return sudoUsersArray;
    }

    public void setSudoUsersArray(List<String> sudoUsersArray) {
        this.sudoUsersArray = sudoUsersArray;
    }

    public void setIptablesRules(List<String> iptablesRules) {
        this.iptablesRules = iptablesRules;
    }

    public List<String> getIptablesRules() {
        return iptablesRules;
    }
}
