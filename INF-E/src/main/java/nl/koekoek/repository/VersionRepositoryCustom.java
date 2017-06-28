package nl.koekoek.repository;

import nl.koekoek.model.Version;

public interface VersionRepositoryCustom {

    /**
     * Finds the current version of a server by.
     * @param versionName name of the version
     * @param hostName name of the server
     * @return a version
     */
    Version findCurrentVersionByName(String versionName, String hostName);
}
