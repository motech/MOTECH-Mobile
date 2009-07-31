/*
 * ResponseDetailsDAO is an interface that defines only methods and attributes that are specific to ResponseDetails entity
 */

package com.dreamoval.motech.core.dao;

import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.model.ResponseDetailsImpl;

/**
 * Date: 
 * @author Joseph Djomeda
 * Email: joseph@dreamoval.com
 */
public interface ResponseDetailsDAO extends GenericDAO<ResponseDetails, Long>{

    public boolean storeResponse(ResponseDetails responseDetails);


}
