package nl.koekoek.controller;

import nl.koekoek.controller.model.PersistentServerDataJsonModel;
import nl.koekoek.model.PersistentServerData;
import nl.koekoek.service.PersistentServerDataService;
import nl.koekoek.service.ServerService;
import nl.koekoek.support.RestResult;

import org.jarbframework.constraint.violation.UniqueKeyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for PersistentServerData service.
 * @author Kevin van Leeuwen
 * 
 */
@Controller
@RequestMapping(value = "/persistentdata")
public class PersistentServerDataController {

    private final PersistentServerDataService persistentServerDataService;
    private final ServerService serverService;

    /**
     * Contructor for PersistentServerDataController.
     * @param persistentServerDataService the service for persistentServerData
     * @param serverService the server service
     * @param versionController the versioncController to saves versions
     * @param userController the userController to save users
     * @param iptableController the iptableController to save the iptables
     */
    @Autowired
    public PersistentServerDataController(PersistentServerDataService persistentServerDataService, ServerService serverService,
            VersionController versionController, UserController userController, IptableController iptableController) {
        this.persistentServerDataService = persistentServerDataService;
        this.serverService = serverService;
    }

    /**
     * Creates a persistentServerData.
     * @param persistentServerDataProcessor Json model for persistentServerData
     * @return Restresult of the created persistentServerData
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResult<PersistentServerData> create(@RequestBody PersistentServerDataJsonModel persistentServerDataProcessor) {
        PersistentServerData newPersistentServerData = persistentServerDataProcessor.createPersistentServerData(serverService);
        persistentServerDataService.addVersions(persistentServerDataProcessor.getVersionsData(), persistentServerDataProcessor.getServer(),
                persistentServerDataProcessor.getMeasurementTime());

        persistentServerDataService.addUsers(persistentServerDataProcessor.getUsersArray(), persistentServerDataProcessor.getSudoUsersArray(),
                persistentServerDataProcessor.getServer(), persistentServerDataProcessor.getMeasurementTime());

        persistentServerDataService.addIpTables(persistentServerDataProcessor.getIptablesRules(), persistentServerDataProcessor.getServer());
        return create(newPersistentServerData);
    }

    /**
     * Checks for certain errors when saving data.
     * @param persistentServerData to be saved
     * @return error or success
     */
    private RestResult<PersistentServerData> create(PersistentServerData persistentServerData) {
        try {
            return RestResult.success(persistentServerDataService.addPersistentServerData(persistentServerData));
        } catch (UniqueKeyViolationException e) {
            return RestResult.error("PersistentServerData " + persistentServerData.getTimeOfMeasurement().toString() + " already exists.");
        }
    }
}
