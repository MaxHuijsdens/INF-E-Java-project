package nl.koekoek.repository;

import nl.koekoek.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Customer model.
 * @author Kevin van Leeuwen
 * 
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}