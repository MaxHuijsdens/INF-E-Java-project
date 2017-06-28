package nl.koekoek.repository;

import nl.koekoek.model.PersistentServerData;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for PersistentServerData model.
 * @author Kevin van Leeuwen
 * 
 */
public interface PersistentServerDataRepository extends JpaRepository<PersistentServerData, Long> {

}
