package nl.koekoek.repository;

import java.util.List;

import nl.koekoek.model.Sla;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SlaRepository extends JpaRepository<Sla, Long> {

    /**
     * find sla's by.
     * @param customerId is of the customer
     * @return found sla's
     */
    List<Sla> findByCustomerId(long customerId);
}
