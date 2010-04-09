package org.motechproject.mobile.omp.manager.intellivr;


import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.model.MStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:META-INF/test-omp-config.xml"})
public class IntellIVRGatewayMessageHandlerTest {

	@Resource
	IntellIVRGatewayMessageHandler intellIVRMessageHandler;
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testStatusMapping() {
		
		Map<String, MStatus> expected = new HashMap<String, MStatus>();
		
		expected.put("0000", MStatus.CANCELLED);
		expected.put("0001", MStatus.CANCELLED);
		expected.put("0002", MStatus.CANCELLED);
		expected.put("0003", MStatus.CANCELLED);
		expected.put("0004", MStatus.CANCELLED);
		expected.put("0005", MStatus.CANCELLED);
		expected.put("0006", MStatus.CANCELLED);
		expected.put("0007", MStatus.CANCELLED);
		expected.put("0008", MStatus.CANCELLED);
		expected.put("0009", MStatus.CANCELLED);
		expected.put("0010", MStatus.CANCELLED);
		expected.put("OK", MStatus.PENDING);
		expected.put("COMPLETED", MStatus.DELIVERED);
		expected.put("REJECTED", MStatus.FAILED);
		expected.put("BUSY", MStatus.FAILED);
		expected.put("CONGESTION", MStatus.FAILED);
		expected.put("NOANSWER", MStatus.FAILED);
		expected.put("INTERNALERROR", MStatus.FAILED);
		
		for ( String code : expected.keySet()) {
			assertEquals(expected.get(code), intellIVRMessageHandler.lookupStatus(code));
		}
	
		
	}

}
