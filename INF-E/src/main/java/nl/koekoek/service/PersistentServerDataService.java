package nl.koekoek.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nl.koekoek.model.Iptable;
import nl.koekoek.model.PersistentServerData;
import nl.koekoek.model.Server;
import nl.koekoek.model.ServerIptable;
import nl.koekoek.model.ServerUser;
import nl.koekoek.model.ServerUserChange;
import nl.koekoek.model.ServerVersion;
import nl.koekoek.model.User;
import nl.koekoek.model.Version;
import nl.koekoek.repository.PersistentServerDataRepository;
import org.joda.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * implementation of a PersistentServerDataService interface.
 * @author Kevin van Leeuwen
 * 
 */
@Service
@Transactional
public class PersistentServerDataService {

    private PersistentServerDataRepository persistentServerDataRepository;
    
    private VersionService versionService;
    private UserService userService;
    private IptableService iptableService;
    /**
     * Default constructor.
     */
    public PersistentServerDataService() {

    }

    /**
     * Constructor for a PersistentServerDataServiceImpl object.
     * @param persistentServerDataRepository The repository you want to use in this service
     * class
     * @param versionService
     * @param userService
     */
    @Autowired
    public PersistentServerDataService(PersistentServerDataRepository persistentServerDataRepository, VersionService versionService, UserService userService, IptableService iptableService) {
        this.persistentServerDataRepository = persistentServerDataRepository;
        this.versionService = versionService;
        this.userService = userService;
        this.iptableService = iptableService;
    }

    /**
     * Store a PersistentServerData object in the database.
     * @param persistentServerData The PersistentServerData object you want to store in the database
     * @return The stored PersistentServerData
     */
    public PersistentServerData addPersistentServerData(PersistentServerData persistentServerData) {
        return persistentServerDataRepository.save(persistentServerData);
    }
    
    public void addVersions(Map<String, String> versionData, Server server, LocalDateTime timeOfMeasurement){
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
    
    public void addUsers(List<String> userData, List<String> sudoUserData, Server server, LocalDateTime timeOfMeasurement) {
        List<Long> addedUsers = new ArrayList<Long>();
        for (String u : userData) {
            if (userService.getUserByName(u) == null) {
                User user = new User(u);
                userService.addUser(user);
            }
            User user = userService.getUserByName(u);
            ServerUser serverUser = null;
            if (userService.getServerUser(server.getId(), user.getId()) == null) {
                serverUser = new ServerUser(server, user);
                userService.addServerUser(serverUser);

                ServerUserChange serverUserChange = new ServerUserChange(server, user, timeOfMeasurement, true);
                userService.addServerUserChange(serverUserChange);
            } else {
                serverUser = userService.getServerUser(server.getId(), user.getId());
            }
            if (serverUser != null) {
                for (String sudoUser : sudoUserData) {
                    if (user.getName().equals(sudoUser)) {
                        serverUser.setSudo(true);
                        userService.addServerUser(serverUser);
                    }
                }
            }
            addedUsers.add(user.getId());
        }
        List<ServerUser> deletedUsers = userService.getUserNotInServer(addedUsers, server.getId());
        for (ServerUser su : deletedUsers) {
            userService.deleteServerUser(su);
            ServerUserChange serverUserChange = new ServerUserChange(server, su.getUser(), timeOfMeasurement, false);
            userService.addServerUserChange(serverUserChange);
        }
    }
    
    public void addIpTables(List<String> ipTableList, Server server) {
        for (String iptableRule : ipTableList) {
            if (iptableService.findIptableByRule(iptableRule) == null) {
                Iptable iptable = new Iptable(iptableRule);
                iptableService.saveIptable(iptable);
            }
            Iptable iptable = iptableService.findIptableByRule(iptableRule);
            if (iptableService.findServerIpTable(server.getId(), iptable.getId()) == null) {
                ServerIptable serverIptable = new ServerIptable(server, iptable);
                iptableService.saveServerIptable(serverIptable);
            } else {
                iptableService.saveServerIptable(iptableService.findServerIpTable(server.getId(), iptable.getId()));
            }
        }
    };
    
}
