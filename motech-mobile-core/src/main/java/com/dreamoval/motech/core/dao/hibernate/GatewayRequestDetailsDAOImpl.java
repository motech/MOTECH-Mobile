/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.GatewayRequestDetailsDAO;
import com.dreamoval.motech.core.model.GatewayRequestDetailsImpl;
import org.apache.log4j.Logger;

/**
 *  Date : Oct 1, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class GatewayRequestDetailsDAOImpl<T extends GatewayRequestDetailsImpl> extends HibernateGenericDAOImpl<GatewayRequestDetailsImpl> implements GatewayRequestDetailsDAO<GatewayRequestDetailsImpl> {
private static Logger logger = Logger.getLogger(GatewayRequestDetailsImpl.class);
}
