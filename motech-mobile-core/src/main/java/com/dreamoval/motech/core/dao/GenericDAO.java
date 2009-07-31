/*
 * This GenericDAO is an interface that defines all the basic CRUD operation
 * It's should be implemented by an Generic abstract class that in turn various
 * Dao implementation should inherits from to have access.
 */

package com.dreamoval.motech.core.dao;


import java.io.Serializable;
import java.util.List;

/**
 *  Date : Jul 31, 2009
 * @author joseph Djomeda
 *  Email : joseph@dreamoval.com
 */
public interface GenericDAO <T, ID extends Serializable> {

    /**
     * Gets all objects of type T
     * @return List of entity Type T
     */
    List<T> getAll();
    
    /**
     *  Get all object based on the Example passed to it
     * @param exampleInstance
     * @return List of entity type T
     */
    List<T> findByExample(T exampleInstance);

    /**
     * Gets a particular entity of type T based on the id parameter
     * @param id id of type ID of the entity instance to be retrieved
     * @param lock specifies if transaction lock mode should be applied
     * @return entity of type T
     */
    T findById(ID id, boolean lock);
    
    /**
     * Saves the entity of type T
     * @param entity
     * @return entity of type T
     */
    T makePersistent(T entity);

    /**
     *  Deletes entity of type T
     * @param entity
     */
    void makeTransient(T entity);

}
