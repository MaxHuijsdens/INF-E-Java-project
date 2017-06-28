/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.controller.model;

public interface JsonModel<T> {

    /**
     * Build a new entity
     * @return entity
     */
    T buildNew();

    /**
     * Updates a model
     * @param entity to be updated
     * @return entity
     */
    T mergeInto(T entity);

}
