package org.motechproject.mobile.omp.manager.intellivr;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.motechproject.mobile.core.model.GatewayRequest;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

public class ConvertSerializedIVRSessionsBean {

	private IVRDAO ivrDao;
	private Resource ivrSessionSerialResource;
	private long bundlingDelay;
	private boolean dryRun;
	
	public IVRDAO getIvrDao() {
		return ivrDao;
	}

	public void setIvrDao(IVRDAO ivrDao) {
		this.ivrDao = ivrDao;
	}

	public Resource getIvrSessionSerialResource() {
		return ivrSessionSerialResource;
	}

	public void setIvrSessionSerialResource(Resource ivrSessionSerialResource) {
		this.ivrSessionSerialResource = ivrSessionSerialResource;
	}
	
	public long getBundlingDelay() {
		return bundlingDelay;
	}

	public void setBundlingDelay(long bundlingDelay) {
		this.bundlingDelay = bundlingDelay;
	}

	public boolean isDryRun() {
		return dryRun;
	}

	public void setDryRun(boolean dryRun) {
		this.dryRun = dryRun;
	}

	@Transactional
	public void convert() {
		
		if ( dryRun )
			System.out.println("WARNING - Dry run set to true.  No data will be written to database.");

		Map<String, IVRSession> ivrSessions = loadIvrSessions();

		for ( IVRSession session : ivrSessions.values() ){
			Date now = new Date();
			IVRCallSession newSession = new IVRCallSession(
					session.getUserId()		== null ? null : session.getUserId(), 
					session.getPhone()		== null ? null : session.getPhone(), 
					session.getLanguage()	== null ? null : session.getLanguage(), 
					session.getUserId()		== null ? IVRCallSession.INBOUND : IVRCallSession.OUTBOUND, 
					session.getAttempts(), 
					session.getDays(), 
					session.getState(), 
					getNextAttemptDate(session), 
					session.getState() 		== IVRSession.OPEN ? addToDate(now, GregorianCalendar.MILLISECOND, (int)bundlingDelay) : getNextAttemptDate(session));
			if ( session.getState() == IVRSession.REPORT_WAIT ) {
				IVRCall call = new IVRCall(
						new Date(),
						null, 
						null, 
						0, 
						session.getSessionId(), 
						IVRCallStatus.REQUESTED, 
						session.getUserId() == null ? "Client called IVR system" : "Call request accepted", 
						newSession);
				newSession.getCalls().add(call);
			}
			for ( GatewayRequest request : session.getGatewayRequests() )
				newSession.getMessageRequests().add(request.getMessageRequest());
			if ( !dryRun )
				ivrDao.saveIVRCallSession(newSession);
			System.out.println("Created session: " + newSession);
			for ( IVRCall c : newSession.getCalls())
				System.out.println("Added call: " + c);
		}
		
	}

	private Date getNextAttemptDate(IVRSession session) {
		
		Date nextAttemptDate = null;
		boolean dateSet = false;
		
		for ( GatewayRequest gr : session.getGatewayRequests() ) {
			if ( !dateSet && gr.getDateFrom() != null ) {
				nextAttemptDate = gr.getDateFrom();
				dateSet = true;
			}
		}
					
		return nextAttemptDate == null ? new Date() : nextAttemptDate;
	}
	
	@SuppressWarnings("unchecked")
	protected Map<String, IVRSession> loadIvrSessions() {

		Map<String, IVRSession> loadedSessions = new HashMap<String, IVRSession>();

		if ( ivrSessionSerialResource != null ) {

			ObjectInputStream objIn = null;

			try {

				objIn = new ObjectInputStream(new FileInputStream(ivrSessionSerialResource.getFile()));

				loadedSessions = (Map<String, IVRSession>)objIn.readObject();

				for ( IVRSession s : loadedSessions.values() ) {
					System.out.println("Loaded existing session " + s.getSessionId());
				}

				return loadedSessions;

			} catch (IOException e) {
				System.out.println("Cached IVRSessions not loaded due to following error: " + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println("Cached IVRSessions not loaded due to following error: " + e.getMessage());
			} finally {
				if ( objIn != null )
					try {
						objIn.close();
					} catch (IOException e) {
					}
			}

		}

		return loadedSessions;
		
	}
	
	private Date addToDate(Date start, int field, int amount) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(start);
		cal.add(field, amount);
		return cal.getTime();
	}


}
