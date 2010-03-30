package org.motechproject.mobile.omp.manager.intellivr;

import org.motechproject.omp.manager.intellivr.AudioType;
import org.motechproject.omp.manager.intellivr.GetIVRConfigRequest;
import org.motechproject.omp.manager.intellivr.ReportType;
import org.motechproject.omp.manager.intellivr.ResponseType;
import org.motechproject.omp.manager.intellivr.StatusType;
import org.motechproject.omp.manager.intellivr.RequestType.Vxml;
import org.motechproject.omp.manager.intellivr.RequestType.Vxml.Prompt;

public class IntellIVRBean implements GetIVRConfigRequestHandler, ReportHandler {

	public ResponseType handleRequest(GetIVRConfigRequest request) {
		ResponseType rt = new ResponseType();
		rt.setStatus(StatusType.OK);
		rt.setLanguage("ENGLISH");
		rt.setPrivate("PRIVATE");
		rt.setReportUrl("http://130.111.123.83:8080/motech-mobile-webapp/intellivr");
		rt.setTree("TestTree");
		Vxml vxml = new Vxml();
		vxml.setPrompt(new Prompt());
		AudioType audio = new AudioType();
		audio.setSrc("test1.wav");
		vxml.getPrompt().getAudioOrBreak().add(audio);
		rt.setVxml(vxml);
		return rt;
	}

	public ResponseType handleReport(ReportType report) {
		ResponseType rt = new ResponseType();
		rt.setStatus(StatusType.OK);
		return rt;
	}

}
