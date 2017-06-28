package nl.koekoek.repository;

import java.util.List;

import nl.koekoek.model.ServerIptable;
import nl.koekoek.model.ServerJson;
import nl.koekoek.model.ServerUser;
import nl.koekoek.model.ServerUserChange;
import nl.koekoek.model.ServerVersion;
import nl.koekoek.model.ServerVersionJson;

public interface ServerRepositoryCustom {

    /**
     * Get all servers with last note
     * @return Server object
     */
    List<ServerJson> getAllServers();
    
    /**
     * Get all current versions for each server.
     * @return
     */
    List<ServerVersionJson> getServerVersionsList(Long slaId);

    /**
     * Get all current versions for one server.
     * @param serverId the server id
     * @return ServerVersion object 
     */
    List<ServerVersion> getLatestServerVersions(long serverId);

    /**
     * Get all not known users for a server.
     * @param serverId id of the server
     * @return list with users
     */
    List<ServerUser> getAllNotKnownServerUsers(long serverId);

    /**
     * Get all not known user changes for a server.
     * @param serverId id of the server
     * @return list with user changes
     */
    List<ServerUserChange> getAllNotKnownServerUserChange(long serverId);

    /**
     * Get all not known iptables for a server.
     * @param serverId id of the server
     * @return list with iptables
     */
    List<ServerIptable> getAllNotKnownServerIptable(long serverId);
}
