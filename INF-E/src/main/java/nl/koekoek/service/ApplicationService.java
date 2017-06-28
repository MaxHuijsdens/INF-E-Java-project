/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.service;

import nl.koekoek.model.CurrentDatabaseVersion;
import nl.koekoek.repository.CurrentDatabaseVersionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author Jeroen van Schagen
 * @since Aug 25, 2014
 */
@Service
public class ApplicationService {

    @Value("${application.version}")
    private String applicationVersion;

    @Autowired
    private CurrentDatabaseVersionRepository databaseVersionRepository;

    public VersionInfo getVersionInfo() {
        CurrentDatabaseVersion databaseVersion = databaseVersionRepository.getCurrentDatabaseVersion();
        return new VersionInfo(applicationVersion, databaseVersion);
    }

    public static class VersionInfo {

        private final String applicationVersion;

        private final CurrentDatabaseVersion databaseVersion;

        public VersionInfo(String applicationVersion, CurrentDatabaseVersion databaseVersion) {
            this.applicationVersion = applicationVersion;
            this.databaseVersion = databaseVersion;
        }

        public String getApplicationVersion() {
            return applicationVersion;
        }

        public CurrentDatabaseVersion getDatabaseVersion() {
            return databaseVersion;
        }

    }

}
