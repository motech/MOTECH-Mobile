package org.motechproject.mobile.imp.serivce.oxd;

import java.util.List;

/**
 * Interface for getting information about defined mobile form studies.
 * 
 * @author batkinson
 * 
 */
public interface StudyDefinitionService {

	List<Object []> getStudies();
	
	String getStudyName(int id);

}
