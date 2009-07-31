/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao;

/**
 *  Date : Jul 31, 2009
 * @author joseph Djomeda
 *  Email : joseph@dreamoval.com
 */
public abstract class DAOFactory {

    public static final Class HIBERNATE = com.dreamoval.motech.core.dao.hibernate.DAOFactoryImpl.class;

    /**
     * Factory method for instantiation of concrete factories
     * @param factory factory to be created
     * @return instance of factory class
     */
    public static DAOFactory instance(Class factory) {
        try {
            return (DAOFactory)factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
               
  }


 public abstract MessageDetailsDAO getMessageDetailsDAO();
 public abstract ResponseDetailsDAO getResponseDetailsDAO();

}
