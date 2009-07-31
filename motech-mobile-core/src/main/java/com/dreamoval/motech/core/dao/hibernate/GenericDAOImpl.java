/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.GenericDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

/**
 *  Date : Jul 31, 2009
 * @author joseph Djomeda
 *  Email : joseph@dreamoval.com
 */
public abstract class GenericDAOImpl<T , ID extends Serializable>  implements GenericDAO<T, ID>{

    private Class<T> persistentClass;
    protected Session session;


    public GenericDAOImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @return the persistentClass
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /**
     * @return the session
     */
    public Session getSession() {
        if(session == null)
            throw new IllegalStateException("Session has not been set on DAO before usage");

        return session;
    }


    /**
     * @param session the session to set
     */
    @SuppressWarnings("unchecked")
    public void setSession(Session session) {
        this.session = session;
    }


     /**
     * Gets all objects of type T
     * @return List of entity Type T
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return findByCriteria();
    }


    /**
     * Perfoms a search based on a criterion or no criterion
     * @param criterion array of criterion or no criterion
     * @return List of entity of type T
     */
    protected List<T> findByCriteria(Criterion... criterion) {

        Criteria crit = getSession().createCriteria(getPersistentClass());
        for(Criterion c :criterion) {
            crit.add(c);
        }
        return crit.list();
    }


    /**
     * Gets a particular entity of type T based on the id parameter
     * @param id id of type ID of the entity instance to be retrieved
     * @param lock specifies if transaction lock mode should be applied
     * @return entity of type T
     */
    @SuppressWarnings("unchecked")
    public T findById(ID id, boolean lock) {
        T entity;
        if(lock)
            entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
        else
            entity = (T) getSession().load(getPersistentClass(), id);
        return entity;
    }

 /**
     * Saves the entity of type T
     * @param entity
     * @return entity of type T
     */
    @SuppressWarnings("unchecked")
    public T makePersistent(T entity) {
     getSession().saveOrUpdate(entity);
     return entity;
    }


    /**
     *  Deletes entity of type T
     * @param entity
     */

    public void makeTransient(T entity) {
        getSession().delete(entity);
    }



    public void flush() {
        getSession().flush();
    }


    public void clear() {
        getSession().clear();
    }

     /**
     *  Get all object based on the Example passed to it
     * @param exampleInstance
     * @return List of entity type T
     */
@SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example =  Example.create(exampleInstance);
//        for (String exclude : excludeProperty) {
//            example.excludeProperty(exclude);
//        }
        crit.add(example);
        return crit.list();
    }


}
