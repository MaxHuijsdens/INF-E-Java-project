package nl.koekoek.repository;

import java.util.List;

import nl.koekoek.model.Measurement;

import org.joda.time.LocalDateTime;

/**
 * Repository for all measurements.
 * @param <T> Measurement class
 * @author Tom
 *
 */
public interface MeasurementRepositoryInterface {

    /**
     * Retrieve the latest  measurement from a server.
     * @param serverId id of the server
     * @return List of all latest measurents per server
     */
    List<Measurement> getLatestMeasurementsPerServer(Long serverId);

    /**
     * Retrieve graphical data as a list of array(date, float/int).
     * @param id The id of the server you want to retrieve data from.
     * @param graphType The data you want to retrieve(example: load).
     * @param startDate The start date
     * @param endDate The end date
     * @param source source of the measurement
     * @return list of array objects.
     */
    List<Number[]> findGraphicalDataByServerId(long id, String graphType, LocalDateTime startDate, LocalDateTime endDate, String source);

}
