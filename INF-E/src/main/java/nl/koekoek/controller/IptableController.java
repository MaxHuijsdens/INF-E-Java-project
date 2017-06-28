package nl.koekoek.controller;

import java.util.List;

import nl.koekoek.model.Iptable;
import nl.koekoek.model.Server;
import nl.koekoek.model.ServerIptable;
import nl.koekoek.service.IptableService;
import nl.koekoek.support.RestResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Bastiaan on 11-8-2014.
 */
@Controller
@RequestMapping(value = "/iptable")
public class IptableController {

    private final IptableService iptableService;

    /**
     * Custom contructor with the service autowired.
     * @param iptableService the service for this controller
     */
    @Autowired
    IptableController(IptableService iptableService) {
        this.iptableService = iptableService;
    }

    /**
     * Returns all iptables.
     * @return List with all iptables
     */
    @RequestMapping(value = "/iptableList", method = RequestMethod.GET)
    @ResponseBody
    public List<Iptable> getIptableList() {
        return iptableService.getAllIptables();
    }

    /**
     * Saves/updates a list of Iptables in the db.
     * @param iptableList list of iptables to be updated
     * @return Restresult for iptables
     */
    @RequestMapping(value = "/saveIptableList", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<List<Iptable>> saveIptableList(@RequestBody List<Iptable> iptableList) {
        return RestResult.success(this.iptableService.saveIptableList(iptableList));
    }

    /**
     * Adds iptables to the database that are sent through JSON.
     * @param ipTableList list with iptables to be saved
     * @param server the iptables belong to
     */
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
