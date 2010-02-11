package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.DBSession;
import com.dreamoval.motech.core.dao.GenericDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.apache.log4j.Logger;


/*
 * HibernateGenericDAOImpl is the hibernate implementation of the genericDAO interface
 * that defines the contract of common persistence methods.
 * This class should be extended by  implementation of every domain DAO 
 *
 *  Date : Jul 31, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public abstract class HibernateGenericDAOImpl<T> implements GenericDAO<T> {

    private static Logger logger = Logger.getLogger(HibernateGenericDAOImpl.class);
    private Class<T> persistentClass;
    protected DBSession<Session, Transaction> session;

    public HibernateGenericDAOImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @return the persistentClass
     */
    public Class<T> getPersistentClass() {
        logger.info("Calling getPersistentClass");
        return persistentClass;
    }

    /**
     * @return the session
     */
    public DBSession<Session, Transaction> getDBSession() {
        logger.info("returning a session");
        if (session == null) {
            throw new IllegalStateException("Session has not been set");
        }

        return session;
    }

    /**
     * @param session the session to set
     */
    @SuppressWarnings("unchecked")
    public void setDBSession(DBSession session) {
        logger.info("setting a session");
        this.session = session;
    }

    /**
     * Gets all objects of type T
     * @return List of entity Type T
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        logger.info("calling getAll");
        return findByCriteria();
    }

    /**
     * Perfoms a search based on a criterion or no criterion
     * @param criterion array of criterion or no criterion
     * @return List of entity of type T
     */
    protected List<T> findByCriteria(Criterion... criterion) {
        logger.info("Callint FindByCriteria");
        Criteria crit = getDBSession().getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

    /**
     * Gets a particular entity of type T by id
     * @param id id of type ID of the entity instance to be retrieved
     * @return entity of type T
     */
    @SuppressWarnings("unchecked")
    public T getById(Serializable id) {
        logger.info("Calling getById");
        T entity;

        entity = (T) getDBSession().getSession().load(getPersistentClass(), id);
        return entity;
    }

    /**
     * Saves the entity of type T
     * @param entity
     * @return entity of type T
     */
    @SuppressWarnings("unchecked")
    public T save(T entity) {
        getDBSession().getSession().saveOrUpdate(entity);
        return entity;
    }

    /**
     *  Deletes entity of type T
     * @param entity
     */
    public void delete(T entity) {
        logger.info("Calling delete");
        getDBSession().getSession().delete(entity);
    }

    public void flush() {
        logger.info("Calling session fush");
        getDBSession().getSession().flush();
    }

    public void clear() {
        logger.info("Calling session clear");
        getDBSession().getSession().clear();
    }

    /**
     *  @see {@link com.dreamoval.motech.core.dao.GenericDAO#findByExample(java.lang.Object) 
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        logger.info("Calling findByExample");
        Criteria crit = getDBSession().getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        if (excludeProperty.length != 0) {
            for (String exclude : excludeProperty) {
                example.excludeProperty(exclude);
            }
        }
        crit.add(example);
        return crit.list();
    }
}
