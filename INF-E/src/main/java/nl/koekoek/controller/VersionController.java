package nl.koekoek.controller;

import java.util.Map;

import nl.koekoek.model.Server;
import nl.koekoek.model.ServerVersion;
import nl.koekoek.model.Version;
import nl.koekoek.service.VersionService;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller for the version class.
 * @author bastiaan
 *
 */
@Controller
public class VersionController {

    private final VersionService versionService;

    /**
     * The contructor for the versioncontroller, with autowired versionService.
     * @param versionService the versionService
     */
    @Autowired
    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    /**
     * Adds a version to a server when a new one is added.
     * @param versionData all versions
     * @param server the server where the versions belong
     * @param timeOfMeasurement what time it is measured
     */
    public void addVersions(Map<String, String> versionData, Server server, LocalDateTime timeOfMeasurement) {
        for (Map.Entry<String, String> e : versionData.entrySet()) {
            String[] splitString = e.getKey().split("Version");
            String name = splitString[0];
            if (!e.getValue().isEmpty()) {
                Version version = versionService.getVersionByNameAndNumber(name, e.getValue());
                if (version == null) {
                    version = new Version(name, e.getValue());
                    versionService.addVersion(version);
                }
                Version currentVersion = versionService.findCurrentVersionByName(name, server.getHostName());
                if (currentVersion == null || !e.getValue().equals(currentVersion.getVersionNumber())) {
                    ServerVersion serverVersion = new ServerVersion(server, version, timeOfMeasurement);
                    versionService.addServerVersion(serverVersion);
                }
            }
        }
    }
}
