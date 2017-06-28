package nl.koekoek.repository;

import nl.koekoek.model.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Supplier model.
 * @author Kevin van Leeuwen
 * 
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}