package org.motechproject.mobile.web.ivr.intellivr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.motechproject.mobile.omp.manager.intellivr.IVRCallStatsProvider;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class IVRStatsController extends AbstractController implements ResourceLoaderAware  {

	private IVRCallStatsProvider ivrStatsProvider;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("ivrstats");
		
		mav.addObject("fiveMinuteSessionCount", ivrStatsProvider.getCountIVRSessionsInLastMinutes(5));
		mav.addObject("oneHourSessionCount", ivrStatsProvider.getCountIVRCallSessionsInLastHours(1));
		mav.addObject("oneDaySessionCount", ivrStatsProvider.getCountIVRCallSessionsInLastDays(1));
		mav.addObject("allSessionCount", ivrStatsProvider.getCountIVRCallSessions());
		
		mav.addObject("fiveMinuteCallStats", ivrStatsProvider.getIVRCallStatusStatsFromLastMinutes(5));
		mav.addObject("oneHourCallStats", ivrStatsProvider.getIVRCallStatusStatsFromLastHours(1));
		mav.addObject("oneDayCallStats", ivrStatsProvider.getIVRCallStatusStatsFromLastDays(1));
		mav.addObject("allCallStats", ivrStatsProvider.getIVRCallStatusStats());
		
		mav.addObject("recordingStats", ivrStatsProvider.getIVRRecordingStats());
		
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
