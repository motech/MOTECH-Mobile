package org.motechproject.mobile.omp.manager.utils;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.mobile.omp.manager.utils.InMemoryMessageStatusStore;
import org.motechproject.mobile.omp.manager.utils.MessageStatusStore;


public class InMemoryMessageStatusStoreTest {

	MessageStatusStore store;
	
	@Before
	public void setUp() throws Exception {
		store = new InMemoryMessageStatusStore();
	}
	
	@Test
	public void testUpdate() {
	
		String id = "test_id";
		String firstStatus = "test_status_1";
		String secondStatus = "test_status_2";
		
		store.updateStatus(id, firstStatus);
		
		assertEquals(firstStatus, store.getStatus(id));
		
		store.updateStatus(id, secondStatus);
		
		assertEquals(secondStatus, store.getStatus(id));
	
	}

}
