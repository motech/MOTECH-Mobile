package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Set;

import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;

public class IntellIVRRequestHandler implements GatewayManager, GetIVRConfigRequestHandler, ReportHandler {

	private GatewayMessageHandler messageHandler;
	
	public String getMessageStatus(GatewayResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public MStatus mapMessageStatus(GatewayResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails,
			MotechContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMessageHandler(GatewayMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public GatewayMessageHandler getMessageHandler() {
		return messageHandler;
	}

	
	public ResponseType handleRequest(GetIVRConfigRequest request) {
		ResponseType rt = new ResponseType();
		rt.setStatus(StatusType.OK);
		rt.setLanguage("ENGLISH");
		rt.setPrivate("PRIVATE");
		rt.setReportUrl("http://130.111.123.83:8080/motech-mobile-webapp/intellivr");
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
