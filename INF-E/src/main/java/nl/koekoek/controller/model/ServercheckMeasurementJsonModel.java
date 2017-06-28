package nl.koekoek.controller.model;

import java.util.Map;

import nl.koekoek.model.Server;
import nl.koekoek.model.ServercheckMeasurement;
import nl.koekoek.service.MeasurementService;
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
public class ServercheckMeasurementJsonModel {

    private Map<String, String> measurementData;
    private Map<String, Object> serverData;
    @JsonIgnore
    private ServercheckMeasurement servercheckMeasurement;

    /**
     * creates a serverMeasurement.
     * @param serverService the serverService
     * @param measurementService the measurementservice
     * @return created server measurement
     */
    public ServercheckMeasurement createServerMeasurement(ServerService serverService, MeasurementService measurementService) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd-HHmm");
        LocalDateTime measurementTime = formatter.parseLocalDateTime((String) measurementData.get("timeOfMeasurement"));
        Server server = serverService.getServerByHostName((String) serverData.get("hostName"));
        if (server == null) {
            server = new Server();
            server.setHostName((String) serverData.get("hostName"));
            serverService.addServer(server);
        }
        if (measurementService.getServercheckMeasurementByServerTimeSource(server.getId(), measurementTime, "servcheck") != null) {
            servercheckMeasurement = measurementService.getServercheckMeasurementByServerTimeSource(server.getId(), measurementTime, "servcheck");
        } else {
            servercheckMeasurement = new ServercheckMeasurement();
        }
        servercheckMeasurement.setServer(server);
        servercheckMeasurement.setTimeOfMeasurement(measurementTime);
        servercheckMeasurement.setCpuUsage(Double.parseDouble(measurementData.get("cpuUsage")));
        servercheckMeasurement.setLoad(Double.parseDouble(measurementData.get("load")));
        servercheckMeasurement.setDiskIo(Double.parseDouble(measurementData.get("diskIO")));
        servercheckMeasurement.setDiskUsage(Double.parseDouble(measurementData.get("diskUsage")));
        servercheckMeasurement.setInodeUsage(Double.parseDouble(measurementData.get("inodeUsage")));
        servercheckMeasurement.setNetworkIn(Double.parseDouble(measurementData.get("networkIn")));
        servercheckMeasurement.setNetworkOut(Double.parseDouble(measurementData.get("networkOut")));
        servercheckMeasurement.setDiskIo(Double.parseDouble(measurementData.get("diskIO")));
        servercheckMeasurement.setRoundtripTime(Double.parseDouble(measurementData.get("roundtripTime")));
        servercheckMeasurement.setIoWait(Double.parseDouble(measurementData.get("ioWait")));
        servercheckMeasurement.setFreeRam(Double.parseDouble(measurementData.get("ram")));
        servercheckMeasurement.setFreeSwap(Double.parseDouble(measurementData.get("swap")));
        servercheckMeasurement.setTotalMemory(Double.parseDouble(measurementData.get("totalMem")));
        int processesRunning = (int) Math.round(Double.parseDouble(measurementData.get("processesRunning")));
        servercheckMeasurement.setProcessesRunning(processesRunning);
        servercheckMeasurement.setUptime(measurementData.get("uptime"));
        return servercheckMeasurement;
    }

    public ServercheckMeasurement getServerMeasurement() {
        return servercheckMeasurement;
    }

    public void setServerMeasurement(ServercheckMeasurement servercheckMeasurement) {
        this.servercheckMeasurement = servercheckMeasurement;
    }

}
