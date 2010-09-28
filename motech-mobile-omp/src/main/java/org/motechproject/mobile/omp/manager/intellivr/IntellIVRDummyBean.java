package org.motechproject.mobile.omp.manager.intellivr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Provides a response for a test userid '123456789' for generating test content
 * when calling into the ivr system.  All other ids are pased to the real
 * {@link IntellIVRBean}
 * @author fcbrooks
 *
 */
public class IntellIVRDummyBean extends IntellIVRBean { 
	
	private String testID = "123456789";
	
	private Log log = LogFactory.getLog(IntellIVRDummyBean.class);
	
	public ResponseType handleRequest(GetIVRConfigRequest request) {
		if ( request.getUserid().equalsIgnoreCase(testID) ) {
			log.info("Received request for id " + request.getUserid());
			ResponseType rt = new ResponseType();
			rt.setStatus(StatusType.OK);
			rt.setLanguage(this.getDefaultLanguage());
			rt.setPrivate(testID);
			rt.setReportUrl(this.getReportURL());
			rt.setTree(this.getDefaultTree());
			RequestType.Vxml vxml = new RequestType.Vxml();
			vxml.setPrompt(new RequestType.Vxml.Prompt());
			AudioType audio = new AudioType();
			audio.setSrc(this.getDefaultReminder());
			vxml.getPrompt().getAudioOrBreak().add(audio);
			rt.setVxml(vxml);
			return rt;
		} else 
			return super.handleRequest(request);
	}
	
}
