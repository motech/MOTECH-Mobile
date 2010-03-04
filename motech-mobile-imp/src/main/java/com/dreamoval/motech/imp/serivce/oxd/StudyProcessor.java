/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.serivce.oxd;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.fcitmuk.epihandy.DeserializationListenerAdapter;
import org.fcitmuk.epihandy.FormData;
import org.fcitmuk.epihandy.StudyData;
import org.fcitmuk.epihandy.StudyDataList;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com) and Brent Atkinson
 * Date Created: Mar 3, 2010
 */
public class StudyProcessor extends DeserializationListenerAdapter {

	private static Logger log = Logger.getLogger(StudyProcessor.class);

	int numForms = 0;
	StudyDataList model;
	List<List<String>> studyFormXmlList = new ArrayList<List<String>>();

	@Override
	public void processingStudy(StudyData studyData) {
		List<String> formList = new ArrayList<String>();
		studyFormXmlList.add(formList);
	}

	@Override
	public void formProcessed(StudyData studyData, FormData formData, String xml) {
		// Get the last list (should be our study), and add xml to end of it
		int lastFormListIndex = studyFormXmlList.size() - 1;
		List<String> lastFormList = studyFormXmlList.get(lastFormListIndex);
		lastFormList.add(xml);
		numForms++;
	}

	@Override
	public void complete(StudyDataList studyDataList, List<String> xmlForms) {
		model = studyDataList;
	}

	/**
	 * Returns the total number of forms processed so far.
	 *
	 * @return
	 */
	public int getNumForms() {
		return numForms;
	}

	/**
	 * Returns the deserialized object model read off the wire.
	 *
	 * @return
	 */
	public StudyDataList getModel() {
		return model;
	}

	/**
	 * Returns 2-dimensional array indexed by study, then form
	 *
	 * @return
	 */
	public String[][] getConvertedStudies() {
		String[][] studies = new String[studyFormXmlList.size()][];
		for (int i = 0; i < studies.length; i++)
			studies[i] = studyFormXmlList.get(i).toArray(new String[] {});
		return studies;
	}

	public void failed(Throwable t) {
		log.error("failed while processing upload", t);
	}
}
