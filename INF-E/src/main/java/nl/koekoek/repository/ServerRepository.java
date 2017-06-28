package nl.koekoek.repository;

import java.util.List;

import nl.koekoek.model.Server;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Server model.
 * @author Tom
 * 
 */
public interface ServerRepository extends ServerRepositoryCustom, JpaRepository<Server, Long> {

    /**
     * Find a Server by its ipv4 address.
     * @param ipv4 The ipv4 address of the server you want to find.
     * @return Server object
     */
    Server findByIpv4(String ipv4);

    /**
     * Gets a server by its id.
     * @param id the server id
     * @return server object
     */
    Server findById(Long id);

    /**
     * Find a Server by its host name.
     * @param hostName The host name of the server you want to find.
     * @return Server object
     */
    Server findByHostName(String hostName);

    /**
     * Find a Server by its host name.
     * @param hostName The host name of the server you want to find.
     * @return Server object
     */
    List<Server> findByHostNameStartingWith(String hostName);

    /**
     * Finds all server by. 
     * @param slaId id of the sla
     * @return list of found servers
     */
    List<Server> findBySlaId(long slaId);
}
