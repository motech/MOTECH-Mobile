package org.motechproject.mobile.web.ivr.intellivr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.motechproject.mobile.omp.manager.intellivr.IVRCall;
import org.motechproject.mobile.omp.manager.intellivr.IVRCallSession;
import org.motechproject.mobile.omp.manager.intellivr.IVRCallStatsProvider;
import org.motechproject.mobile.omp.manager.intellivr.IVRMenu;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class IVRStatsController extends AbstractController implements ResourceLoaderAware  {

	private IVRCallStatsProvider ivrStatsProvider;
	
	@Transactional
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String userId = request.getParameter("userid");
		String phone = request.getParameter("phone");
		
		ModelAndView mav = null;
		
		if ( (userId != null && !userId.equalsIgnoreCase(""))
				|| (phone != null && !phone.equalsIgnoreCase("")) ) {
			
			List<IVRCallSession> sessions = null;
			
			if ( userId != null ) 
				sessions = ivrStatsProvider.getIVRCallSessionsForUser(userId);
			else 
				sessions = ivrStatsProvider.getIVRCallSessionsForPhone(phone);
			
			StringBuilder builder = new StringBuilder();
			
			for ( IVRCallSession s : sessions ) {
				builder.append("\n\n" + s.toString());
				for ( IVRCall c : s.getCalls() ) {
					builder.append("\n\t" + c.toString());
					for ( IVRMenu m : c.getMenus() ) {
						builder.append("\n\t\t" + m.toString());
					}
				}
			}
			
			response.setContentType("text/plain");
			response.getOutputStream().print(builder.toString());
				
		} else {

			mav = new ModelAndView("ivrstats");

			mav.addObject("fiveMinuteSessionCount", ivrStatsProvider.getCountIVRSessionsInLastMinutes(5));
			mav.addObject("oneHourSessionCount", ivrStatsProvider.getCountIVRCallSessionsInLastHours(1));
			mav.addObject("oneDaySessionCount", ivrStatsProvider.getCountIVRCallSessionsInLastDays(1));
			mav.addObject("allSessionCount", ivrStatsProvider.getCountIVRCallSessions());

			mav.addObject("fiveMinuteCallStats", ivrStatsProvider.getIVRCallStatusStatsFromLastMinutes(5));
			mav.addObject("oneHourCallStats", ivrStatsProvider.getIVRCallStatusStatsFromLastHours(1));
			mav.addObject("oneDayCallStats", ivrStatsProvider.getIVRCallStatusStatsFromLastDays(1));
			mav.addObject("allCallStats", ivrStatsProvider.getIVRCallStatusStats());

			mav.addObject("recordingStats", ivrStatsProvider.getIVRRecordingStats());

		}

		return mav;
		
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub
		
	}

	public IVRCallStatsProvider getIvrStatsProvider() {
		return ivrStatsProvider;
	}

	public void setIvrStatsProvider(IVRCallStatsProvider ivrStatsProvider) {
		this.ivrStatsProvider = ivrStatsProvider;
	}

}
