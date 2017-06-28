package nl.koekoek.repository;

import nl.koekoek.model.Measurement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends MeasurementRepositoryInterface, JpaRepository<Measurement, Long> {

}
