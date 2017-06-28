/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.koekoek.model.CurrentDatabaseVersion;

import org.springframework.stereotype.Repository;

/**
 * Current database version repository. 
 *
 * @author Jeroen van Schagen
 * @since Aug 25, 2014
 */
@Repository
public class CurrentDatabaseVersionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Retrieve the current database version.
     * @return the current database version
     */
    public CurrentDatabaseVersion getCurrentDatabaseVersion() {
        return entityManager.createQuery("from CurrentDatabaseVersion", CurrentDatabaseVersion.class).getSingleResult();
    }

}
