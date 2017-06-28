package nl.koekoek.service;

import java.util.List;

import nl.koekoek.model.Iptable;
import nl.koekoek.model.ServerIptable;
import nl.koekoek.repository.IpTableRepository;
import nl.koekoek.repository.ServerIptableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bastiaan on 11-8-2014.
 */
@Service
@Transactional
public class IptableService {

    private IpTableRepository iptableRepository;
    private ServerIptableRepository serverIptableRepository;

    /**
     * Default constructor.
     */
    public IptableService() {

    };

    /**
     * Autowired constructor.
     * @param iptableRepository object
     * @param serverIptableRepository object
     */
    @Autowired
    public IptableService(IpTableRepository iptableRepository, ServerIptableRepository serverIptableRepository) {
        this.iptableRepository = iptableRepository;
        this.serverIptableRepository = serverIptableRepository;
    };

    /**
     * Saves a iptable.
     * @param ipTable to be saved
     * @return saved iptable
     */
    public Iptable saveIptable(Iptable ipTable) {
        return this.iptableRepository.save(ipTable);
    };

    /**
     * Finds a iptable by its id.
     * @param id of the iptable
     * @return found iptable
     */
    public Iptable findIptable(long id) {
        return this.iptableRepository.findOne(id);
    };

    /**
     * finds a iptable by its tule.
     * @param rule of the iptable
     * @return found iptable
     */
    public Iptable findIptableByRule(String rule) {
        return this.iptableRepository.findByTableRule(rule);
    };

    /**
     * Gets all iptables.
     * @return list of all iptables
     */
    public List<Iptable> getAllIptables() {
        return this.iptableRepository.findAll();
    };

    /**
     * Saves a serverIptable.
     * @param serverIpTable to be saved
     * @return saved iptable
     */
    public ServerIptable saveServerIptable(ServerIptable serverIpTable) {
        return this.serverIptableRepository.save(serverIpTable);
    };

    /**
     * Find a server iptable by serverid and iptableid.
     * @param serverId id of the server
     * @param iptableId id of the iptable
     * @return found serverIptable
     */
    public ServerIptable findServerIpTable(long serverId, long iptableId) {
        return this.serverIptableRepository.findByServerIdAndIptableId(serverId, iptableId);
    };

    /**
     * Saves a list of iptables.
     * @param iptableList list of iptables
     * @return saved list
     */
    public List<Iptable> saveIptableList(List<Iptable> iptableList) {
        return this.iptableRepository.save(iptableList);
    };

}
