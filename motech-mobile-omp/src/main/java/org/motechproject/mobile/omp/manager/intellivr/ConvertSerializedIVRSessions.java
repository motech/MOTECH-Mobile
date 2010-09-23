package org.motechproject.mobile.omp.manager.intellivr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConvertSerializedIVRSessions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/conv-ivr-session-config.xml"} );
		
		ConvertSerializedIVRSessionsBean convBean = (ConvertSerializedIVRSessionsBean)context.getBean("convBean");
		
		convBean.convert();
		
	}

}
