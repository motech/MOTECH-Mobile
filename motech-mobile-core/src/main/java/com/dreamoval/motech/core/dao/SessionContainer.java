/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao;

import org.hibernate.Session;



/**
 *
 * @author Jojo
 */
public interface SessionContainer {

   public Session requestSession();

}
