package nl.koekoek.service;

import java.util.List;

import nl.koekoek.model.Measurement;
import nl.koekoek.model.N2Measurement;
import nl.koekoek.model.ServercheckMeasurement;
import nl.koekoek.repository.MeasurementRepository;
import nl.koekoek.repository.N2MeasurementRepository;
import nl.koekoek.repository.ServercheckMeasurementRepository;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Measurement service, talks to the repository.
 * @author bastiaan
 *
 */
@Service
@Transactional
public class MeasurementService {

    private MeasurementRepository measurementRepository;
    private ServercheckMeasurementRepository servercheckMeasurementRepository;
    private N2MeasurementRepository n2MeasurementRepository;

    /**
     * Constructor.
     * @param measurementRepository object
     * @param servercheckMeasurementRepository object
     * @param n2MeasurementRepository object
     */
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, ServercheckMeasurementRepository servercheckMeasurementRepository,
            N2MeasurementRepository n2MeasurementRepository) {
        this.measurementRepository = measurementRepository;
        this.servercheckMeasurementRepository = servercheckMeasurementRepository;
        this.n2MeasurementRepository = n2MeasurementRepository;
    };

    /**
     * Get the latest measurement for each server with this sla id.
     * @param slaId id of the sla
     * @return list of measurements
     */
    public List<Measurement> getLatestMeasurementsPerServer(Long slaId) {
        return measurementRepository.getLatestMeasurementsPerServer(slaId);
    };

    /**
     * Get metric/graphical data for a server (Load, network in etc).
     * @param id is of the server
     * @param graphType type of the metric
     * @param startDate start date
     * @param endDate end date
     * @param source source of the measurement
     * @return List of number array [Timestamp, Value]
     */
    public List<Number[]> getGraphicalDataByServerId(long id, String graphType, LocalDateTime startDate, LocalDateTime endDate, String source) {
        return measurementRepository.findGraphicalDataByServerId(id, graphType, startDate, endDate, source);
    };

    /**
     * Saves a servercheck measurement.
     * @param measurement to be saved
     * @return saved measurement
     */
    public ServercheckMeasurement saveServercheckMeasurement(ServercheckMeasurement measurement) {
        return servercheckMeasurementRepository.save(measurement);
    };

    /**
     * Gets a servercheckmeasurement by.
     * @param id of the server
     * @param time of the measurement
     * @param source of the measurement
     * @return found measurement
     */
    public ServercheckMeasurement getServercheckMeasurementByServerTimeSource(long id, LocalDateTime time, String source) {
        return servercheckMeasurementRepository.findByServerIdAndTimeOfMeasurementAndDataSource(id, time, source);
    };

    /**
     * saves a n2 measurement.
     * @param n2Measurement to be saved
     * @return saved measurement
     */
    public N2Measurement saveN2Measurement(N2Measurement n2Measurement) {
        return this.n2MeasurementRepository.save(n2Measurement);
    };

}
