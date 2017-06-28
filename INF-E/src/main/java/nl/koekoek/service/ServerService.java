package nl.koekoek.service;

import java.util.List;

import nl.koekoek.model.Server;
import nl.koekoek.model.ServerIptable;
import nl.koekoek.model.ServerJson;
import nl.koekoek.model.ServerUser;
import nl.koekoek.model.ServerUserChange;
import nl.koekoek.model.ServerVersion;
import nl.koekoek.model.ServerVersionJson;
import nl.koekoek.repository.ServerIptableRepository;
import nl.koekoek.repository.ServerRepository;
import nl.koekoek.repository.ServerUserChangeRepository;
import nl.koekoek.repository.ServerUserRepository;
import nl.koekoek.repository.ServerVersionRepository;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * implementation of a ServerService interface.
 * @author Tom
 * 
 */
@Service
@Transactional
public class ServerService {

    private ServerRepository serverRepository;
    private ServerUserRepository serverUserRepository;
    private ServerVersionRepository serverVersionRepository;
    private ServerUserChangeRepository serverUserChangeRepository;
    private ServerIptableRepository serverIptableRepository;

    /**
     * Default contructor.
     */
    public ServerService() {

    }

    /**
     * Constuctor.
     * @param serverRepository object
     * @param serverUserRepository object
     * @param serverVersionRepository object
     * @param serverIptableRepository object
     * @param serverUserChangeRepository object
     */
    @Autowired
    public ServerService(ServerRepository serverRepository, ServerUserRepository serverUserRepository, ServerVersionRepository serverVersionRepository,
            ServerIptableRepository serverIptableRepository,
            ServerUserChangeRepository serverUserChangeRepository) {
        this.serverRepository = serverRepository;
        this.serverUserRepository = serverUserRepository;
        this.serverVersionRepository = serverVersionRepository;
        this.serverUserChangeRepository = serverUserChangeRepository;
        this.serverIptableRepository = serverIptableRepository;
    }

    /**
     * Gets a server by its id.
     * @param id of the server
     * @return Server 
     */
    public Server getServerById(long id) {
        return serverRepository.findById(id);
    }

    /**
     * Retrieve a Server object by its host name.
     * @param hostName The host name of the server you want to retrieve.
     * @return a Server object
     */
    public Server getServerByHostName(String hostName) {
        return serverRepository.findByHostName(hostName);
    }

    /**
     * Retrieve all Server objects that are present in the database.
     * @return a List object with all server object
     */
    public List<ServerJson> getAllServers() {
        return serverRepository.getAllServers();
    }

    /**
     * Retrieve all servers by sla.
     * @param slaId of the sla
     * @return list of found servers
     */
    public List<Server> getAllServersBySla(long slaId) {
        return serverRepository.findBySlaId(slaId);

    };

    /**
     * Retrieve all Server objects their current versions.
     * @return a List object with all server object with their version
     */
    public List<ServerVersionJson> getServerVersionsList(Long slaId) {
        return serverRepository.getServerVersionsList(slaId);
    };

    /**
     * Retrieve all versions off a server.
     * @param serverId the sever id
     * @return a  object with a server object with their version
     */
    public List<ServerVersion> getLatestServerVersions(long serverId) {
        return serverRepository.getLatestServerVersions(serverId);
    };

    /**
     * Get all versions of a server between two dates.
     * @param serverId of the server
     * @param startDate startdate
     * @param endDate enddate
     * @return found versions for a server
     */
    public List<ServerVersion> getServerVersionListByTime(long serverId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.serverVersionRepository.findByServerIdAndTimeOfMeasurementBetween(serverId, startDate, endDate);
    };

    /**
     * Gets all users on a server.
     * @param serverId id from the server
     * @return ServerUser Object
     */
    public List<ServerUser> getAllServerUsersByServer(long serverId) {
        return this.serverUserRepository.findByServerId(serverId);
    }

    /**
     * Get all users on a server that are not known.
     * @param serverId id of the server
     * @return list of users
     */
    public List<ServerUser> getAllNotKnownServerUsersByServer(long serverId) {
        return this.serverRepository.getAllNotKnownServerUsers(serverId);
    };

    /**
     * Get all user changes for a server.
     * @param serverId of the server
     * @return list of serverUserChanges
     */
    public List<ServerUserChange> getAllServerUserChangesByServer(long serverId) {
        return this.serverUserChangeRepository.findByServerId(serverId);
    };

    /**
     * Get all user changes for a server that are not known.
     * @param serverId of the server
     * @return list of serverUserChanges
     */
    public List<ServerUserChange> getAllNotKnownServerUserChangesByServer(long serverId) {
        return this.serverRepository.getAllNotKnownServerUserChange(serverId);
    };

    /**
     * Get all iptables for a server.
     * @param serverId of the server
     * @return list of iptables
     */
    public List<ServerIptable> getAllServerIptableByServerId(long serverId) {
        return this.serverIptableRepository.findByServerId(serverId);
    };

    /**
     * Get all iptables for a server that are not known.
     * @param serverId of the server
     * @return list of iptables
     */
    public List<ServerIptable> getAllNotKnownServerIptableByServerId(long serverId) {
        return this.serverRepository.getAllNotKnownServerIptable(serverId);
    };

    /**
     * Retrieve all Server objects that are present in the database with a specific hostName.
     * @param hostName part of the hostname name
     * @return a List object with all server object
     */
    public List<Server> getServersByHostName(String hostName) {
        return serverRepository.findByHostNameStartingWith(hostName);
    }

    /**
     * Store a Server object in the database.
     * @param server The Server object you want to store in the database
     * @return The stored Server
     */
    public Server addServer(Server server) {
        return serverRepository.save(server);
    }

    /**
     * Update a Server object in the database.
     * @param server The Server object you want to update
     * @return The updated Server
     */
    public Server updateServer(Server server) {
        return serverRepository.save(server);
    }

    /**
     * Delete a Server object.
     * @param server The server you want to delete.
     */
    public void deleteServer(Server server) {
        serverRepository.delete(server);
    }

    /**
     * Get all versions for a server.
     * @param serverId of the server
     * @return list of versions
     */
    public List<ServerVersion> getAllServerVersionsByServer(long serverId) {
        return serverVersionRepository.findByServerId(serverId);
    }
}
