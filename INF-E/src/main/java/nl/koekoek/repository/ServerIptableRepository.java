package nl.koekoek.repository;

import java.util.List;

import nl.koekoek.model.ServerIptable;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bastiaan on 11-8-2014.
 */
public interface ServerIptableRepository extends JpaRepository<ServerIptable, Long> {

    /**
     * Finds a server iptable by.
     * @param serverId id of the server
     * @param iptableId id of the iptable
     * @return ServerIptable
     */
    ServerIptable findByServerIdAndIptableId(long serverId, long iptableId);

    /**
     * Finds a serverIptable by.
     * @param serverId id of the server
     * @return found serveriptables
     */
    List<ServerIptable> findByServerId(long serverId);
}
