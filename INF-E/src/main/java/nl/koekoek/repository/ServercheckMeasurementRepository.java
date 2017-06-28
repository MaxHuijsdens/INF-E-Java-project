package nl.koekoek.repository;

import nl.koekoek.model.ServercheckMeasurement;

import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for ServerMeasurement model.
 * @author Tom
 *
 */
public interface ServercheckMeasurementRepository extends JpaRepository<ServercheckMeasurement, Long> {

    /**
     * Finds a measurement by the following parameters.
     * @param id serverId
     * @param time time of measurement
     * @param source source of the measurement
     * @return Measurement Object
     */
    ServercheckMeasurement findByServerIdAndTimeOfMeasurementAndDataSource(long id, LocalDateTime time, String source);
}
