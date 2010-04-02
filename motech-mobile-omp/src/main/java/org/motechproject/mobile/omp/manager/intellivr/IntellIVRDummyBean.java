package org.motechproject.mobile.omp.manager.intellivr;

public class IntellIVRDummyBean extends IntellIVRBean { 
	
	public ResponseType handleRequest(GetIVRConfigRequest request) {
			ResponseType rt = new ResponseType();
			rt.setStatus(StatusType.OK);
			rt.setLanguage("ENGLISH");
			rt.setPrivate("PRIVATE");
			rt.setReportUrl(reportURL);
			rt.setTree("TestTree");
			RequestType.Vxml vxml = new RequestType.Vxml();
			vxml.setPrompt(new RequestType.Vxml.Prompt());
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
