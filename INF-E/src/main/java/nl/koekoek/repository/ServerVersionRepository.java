package nl.koekoek.repository;

import java.util.List;

import nl.koekoek.model.ServerVersion;

import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerVersionRepository extends JpaRepository<ServerVersion, Long> {

    /**
     * Find serververion by.
     * @param serverId id of the server
     * @return found serverversions
     */
    List<ServerVersion> findByServerId(long serverId);

    /**
     * Find serverVerions by.
     * @param id of the server
     * @param startDate startDate
     * @param endDate endDate
     * @return found serverVersions
     */
    List<ServerVersion> findByServerIdAndTimeOfMeasurementBetween(long id, LocalDateTime startDate, LocalDateTime endDate);
}
