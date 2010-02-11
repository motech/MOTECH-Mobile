package com.dreamoval.motech.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * GenericDAO interface provides common persistence methods contracts.
 * It's implemented by an abstract class that all the implementations of
 * various domain should extend.
 * It's should be also extended by domains DAO interfaces.
 *
 * Date : Jul 31, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 * @author Henry Samspon (henry@dreamoval.com)
 * 
 */
public interface GenericDAO<T> {

    /**
     * Gets all objects of type T
     *
     * @return List of entity Type T
     */
    List<T> getAll();

    /**
     * Get all object by Example
     *
     * @param exampleInstance
     * @return List of entity type T
     */
    List<T> findByExample(T exampleInstance, String... excludeProperty);

    /**
     * Gets a particular entity of type T by id
     *
     * @param id Serializable id of the entity instance to be retrieved
     * @param lock specifies if transaction lock mode should be applied
     * @return entity of type T
     */
    T getById(Serializable id);

    /**
     * Saves the entity of type T
     *
     * @param entity
     * @return entity of type T
     */
    T save(T entity);

    /**
     * Deletes entity of type T
     * 
     * @param entity
     */
    void delete(T entity);

    /**
     * @return the session
     */
    public DBSession getDBSession();

    /**
     * @param session the session to set
     */
    public void setDBSession(DBSession session);
}
