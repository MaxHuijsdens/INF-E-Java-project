package nl.koekoek.repository;

import nl.koekoek.model.N2Measurement;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for N2Measurement model.
 * @author Tom
 *
 */
public interface N2MeasurementRepository extends JpaRepository<N2Measurement, Long> {

}
