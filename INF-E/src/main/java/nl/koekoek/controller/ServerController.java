package nl.koekoek.controller;

import java.util.List;

import nl.koekoek.controller.model.ServerJsonModel;
import nl.koekoek.model.Server;
import nl.koekoek.model.ServerIptable;
import nl.koekoek.model.ServerJson;
import nl.koekoek.model.ServerUser;
import nl.koekoek.model.ServerUserChange;
import nl.koekoek.model.ServerVersion;
import nl.koekoek.model.ServerVersionJson;
import nl.koekoek.service.ServerService;
import nl.koekoek.support.RestResult;

import org.jarbframework.constraint.violation.UniqueKeyViolationException;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller class for server.
 * @author Tom & Kevin van Leeuwen
 * 
 */
@Controller
@RequestMapping(value = "/server")
public class ServerController {

    private final ServerService serverService;

    /**
     * Constructor for ServerController object.
     * @param serverService ServerService object to use in the controller
     */
    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    /**
     * Returns all servers.
     * @return List with all servers
     */
    @RequestMapping(value = "/serverList", method = RequestMethod.GET)
    @ResponseBody
    public List<ServerJson> getServerList() {
        return serverService.getAllServers();
    }

    /**
     * Returns all servers for a specific SLA.
     * @param slaId to get the servers for
     * @return list with all server
     */
    @RequestMapping(value = "/serverList/slaId/{slaId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Server> getServerListBySla(@PathVariable long slaId) {
        return serverService.getAllServersBySla(slaId);
    }

    /**
     * Returns all servers with their current versions.
     * @return List with all servers with their current version
     */
    @RequestMapping(value = "/serverVersionsList", method = RequestMethod.GET)
    @ResponseBody
    public List<ServerVersionJson> getServerVersionsList() {
        return serverService.getServerVersionsList(null);
    }

    /**
     * Returns all server with theur current version for a specific sla.
     * @param slaId to get the server for
     * @return List with all servers with their current version 
     */
    @RequestMapping(value = "/serverVersionsList/slaId/{slaId}", method = RequestMethod.GET)
    @ResponseBody
    public List<ServerVersionJson> getServerVersionsListBySla(@PathVariable Long slaId) {
        return serverService.getServerVersionsList(slaId);
    }

    /**
     * Get all current users on a server.
     * @param serverId to get the users for
     * @param showKnownUsers if the users need to known
     * @return list with all users on a server
     */
    @RequestMapping(value = "/serverUsers/{serverId}/showKnownUsers/{showKnownUsers}", method = RequestMethod.GET)
    @ResponseBody
    public List<ServerUser> getServerUsers(@PathVariable long serverId, @PathVariable boolean showKnownUsers) {
        if (showKnownUsers) {
            return serverService.getAllServerUsersByServer(serverId);
        } else {
            return serverService.getAllNotKnownServerUsersByServer(serverId);
        }
    }

    /**
     * Get users history on a server.
     * @param serverId to get the users for
     * @param showKnownUsers if the users need to known
     * @return list with all users on a server
     */
    @RequestMapping(value = "/serverUserChange/{serverId}/showKnownUsers/{showKnownUsers}", method = RequestMethod.GET)
    @ResponseBody
    public List<ServerUserChange> getServerUserChanges(@PathVariable long serverId, @PathVariable boolean showKnownUsers) {
        if (showKnownUsers) {
            return serverService.getAllServerUserChangesByServer(serverId);
        } else {
            return serverService.getAllNotKnownServerUserChangesByServer(serverId);
        }
    }

    /**
     * Returns all servers with matching hostName.
     * @param hostName the hostname of the server
     * @return List with all servers
     */
    @RequestMapping(value = "/serversByHost/{hostName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Server> getServerListByHostName(@PathVariable String hostName) {
        return serverService.getServersByHostName(hostName);
    }

    /**
     * Get all verions for a server.
     * @param serverId to get the versions for
     * @param showVersionHistory boolean if you want to see the version history
     * @return List with all versions for a server
     */
    @RequestMapping(value = "/serverVersions/{serverId}/showVersionHistory/{showVersionHistory}", method = RequestMethod.GET)
    @ResponseBody
    public List<ServerVersion> getServerVersions(@PathVariable long serverId, @PathVariable boolean showVersionHistory) {
        if (showVersionHistory) {
            return serverService.getAllServerVersionsByServer(serverId);
        } else {
            return serverService.getLatestServerVersions(serverId);
        }

    }

    /**
     * Gets all current versions for a server.
     * @param serverId the id off the server
     * @return a object that holds all versions of a server
     */
    @RequestMapping(value = "/serverVersions/{serverId}/startDate/{startDate}/endDate/{endDate}", method = RequestMethod.GET)
    @ResponseBody
    public List<ServerVersion> getServerVersionsByTime(@PathVariable long serverId, @PathVariable LocalDateTime startDate,
            @PathVariable LocalDateTime endDate) {
        return this.serverService.getServerVersionListByTime(serverId, startDate, endDate);
    };

    /**
     * Get all iptables for a server.
     * @param serverId to get the iptables from
     * @param showKnownIptables boolean if the iptables has to be known or not
     * @return list with iptables
     */
    @RequestMapping(value = "/serverIptables/{serverId}/showKnownIptables/{showKnownIptables}", method = RequestMethod.GET)
    @ResponseBody
    public List<ServerIptable> getServerIptableList(@PathVariable long serverId, @PathVariable boolean showKnownIptables) {
        if (showKnownIptables) {
            return serverService.getAllServerIptableByServerId(serverId);
        } else {
            return serverService.getAllNotKnownServerIptableByServerId(serverId);
        }

    }

    /**
     * Request a single server by its id.
     * @param id The id  of a server
     * @return Server object
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Server getServerById(@PathVariable long id) {
        return serverService.getServerById(id);
    }

    /**
     * Request a single server by its hostname.
     * @param hostName The hostname of a server
     * @return Server object
     */
    @RequestMapping(value = "/hostname/{hostName}", method = RequestMethod.GET)
    @ResponseBody
    public Server getServerByHostname(@PathVariable String hostName) {
        return serverService.getServerByHostName(hostName);
    }

    /**
     * Creates a server.
     * @param serverProcessor represents the json for a server.
     * @return Restresult for a server
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Server> create(@RequestBody ServerJsonModel serverProcessor) {
        Server newServer = serverProcessor.buildNew();
        final RestResult<Server> restResult = createOrEdit(newServer);
        if (restResult.isSuccess()) {
            serverService.addServer(newServer);
        }
        return restResult;
    }

    /**
     * Updates a server.
     * @param serverProcessor represents the json for a server
     * @param server The server to be updated
     * @return Restresult for a server
     */
    @RequestMapping(value = "/update/{server}", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Server> edit(@RequestBody ServerJsonModel serverProcessor, @PathVariable Server server) {
        return createOrEdit(serverProcessor.mergeInto(server));
    }

    private RestResult<Server> createOrEdit(Server server) {
        try {
            return RestResult.success(serverService.updateServer(server));
        } catch (UniqueKeyViolationException e) {
            return RestResult.error("Server " + server.getHostName() + " bestaat al.");
        } catch (DataIntegrityViolationException d) {
            return RestResult.error("Server " + server.getHostName() + " bestaat al.");
        }
    }

    /**
     * Deletes a server.
     * @param server The server to be deleted
     */
    @RequestMapping(value = "/{server}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteServer(@PathVariable Server server) {
        serverService.deleteServer(server);
    }
}
