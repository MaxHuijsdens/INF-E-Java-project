package nl.koekoek.repository;

import nl.koekoek.model.Version;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Version model.
 * @author Tom & Kevin van Leeuwen
 * 
 */
public interface VersionRepository extends VersionRepositoryCustom, JpaRepository<Version, Integer> {

    /**
     * Find a version by the following parameters.
     * @param name the versions name
     * @param number the version number
     * @return Version object
     */
    Version findByNameAndVersionNumber(String name, String number);
}
