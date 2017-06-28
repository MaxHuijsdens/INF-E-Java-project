package nl.koekoek.service;

import nl.koekoek.model.ServerVersion;
import nl.koekoek.model.Version;
import nl.koekoek.repository.ServerVersionRepository;
import nl.koekoek.repository.VersionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of VersionService interface.
 * @author Tom
 * 
 */
@Service
@Transactional
public class VersionService {

    private VersionRepository versionRepository;
    private ServerVersionRepository serverVersionRepository;

    /**
     * Default contructor.
     */
    public VersionService() {

    }

    /**
     * Constructor for a VersionServiceImpl object. 
     * @param versionRepository VersionRepository object you want to use in this service
     * @param serverVersionRepository ServerVersionRepository object you want to use in this service
     */
    @Autowired
    public VersionService(VersionRepository versionRepository, ServerVersionRepository serverVersionRepository) {
        this.versionRepository = versionRepository;
        this.serverVersionRepository = serverVersionRepository;
    }

    /**
     * Retrieve a Version object by its id.
     * @param versionId The id of the Version object you want to retrieve
     * @return Version object
     */
    public Version getVersionById(int versionId) {
        return this.versionRepository.findOne(versionId);
    }

    /**
     * Retrieve a Version object by its type and number.
     * @param type The type of the Version object you want to retrieve
     * @param number The number of the Version object you want to retrieve
     * @return Version object
     */
    public Version getVersionByNameAndNumber(String type, String number) {
        return this.versionRepository.findByNameAndVersionNumber(type, number);
    }

    /**
     * Add a Version object to the database.
     * @param version The Version object you want to add to the database
     * @return The added Version object
     */
    public Version addVersion(Version version) {
        return this.versionRepository.save(version);
    }

    /**
     * find the current version of a server by the server name.
     * @param versionName of the version
     * @param hostName of the server 
     * @return version found
     */
    public Version findCurrentVersionByName(String versionName, String hostName) {
        return versionRepository.findCurrentVersionByName(versionName, hostName);
    }

    /**
     * adds a serverVersion object.
     * @param serverVersion the server version to delete
     * @return ServerVersion object added
     */
    public ServerVersion addServerVersion(ServerVersion serverVersion) {
        return serverVersionRepository.save(serverVersion);
    }

}
