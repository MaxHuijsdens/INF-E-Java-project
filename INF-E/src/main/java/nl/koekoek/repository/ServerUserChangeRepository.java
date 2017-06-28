package nl.koekoek.repository;

import java.util.List;

import nl.koekoek.model.ServerUserChange;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerUserChangeRepository extends JpaRepository<ServerUserChange, Long> {

    /**
     * Gets a serverUser by.
     * @param serverId id of the server
     * @return found serverusers
     */
    List<ServerUserChange> findByServerId(Long serverId);

}
