package org.motechproject.mobile.omp.manager.intellivr;

import static org.junit.Assert.*;

import org.junit.Test;

public class IVRNotificationMappingTest {

	@Test
	public void testSetType() {

		IVRNotificationMapping mapping = new IVRNotificationMapping();
		
		mapping.setType(IVRNotificationMapping.INFORMATIONAL);
		assertEquals(IVRNotificationMapping.INFORMATIONAL, mapping.getType());
		
		mapping.setType(IVRNotificationMapping.REMINDER);
		assertEquals(IVRNotificationMapping.REMINDER, mapping.getType());
		
		mapping.setType("blah");
		assertNull(mapping.getType());
		
	}

}
