package org.motechproject.mobile.omp.manager.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InMemoryMessageStatusStoreTest {

	Log log = LogFactory.getLog(InMemoryMessageStatusStore.class);

	InMemoryMessageStatusStore store;

	String id = "123456", newStatus = "NEWSTATUS",
			updateStatus = "UPDATESTATUS";

	long newTtl = 484, newCleanup = 38948;

	@Before
	public void setUp() throws Exception {
		store = new InMemoryMessageStatusStore();
	}

	@After
	public void tearDown() {
		store = null;
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

	@Test
	public void testUpdateStatusEmpty() {
		store.updateStatus(id, newStatus);
		assertEquals(newStatus, store.getStatus(id));
	}

	@Test
	public void testUpdateStatusExisting() {
		store.updateStatus(id, newStatus);
		store.updateStatus(id, updateStatus);
		assertEquals(updateStatus, store.getStatus(id));
	}

	@Test
	public void testUpdateStatusNull() {
		store.updateStatus(id, newStatus);
		store.updateStatus(id, null);
		assertFalse("Should not contain entry " + id, store.statusMap
				.containsKey(id));
	}

	@Test
	public void testGetStatus() {
		store.updateStatus(id, newStatus);
		assertTrue("Map should contain key " + id, store.statusMap
				.containsKey(id));
		assertEquals(newStatus, store.statusMap.get(id).getStatus());
	}

	@Test
	public void testGetSetTTL() {
		store.setTtl(newTtl);
		assertEquals(newTtl, store.ttl);
		assertEquals(newTtl, store.getTtl());
	}

	@Test
	public void testGetSetInterval() {
		store.setCleanupInterval(newCleanup);
		assertEquals(newCleanup, store.cleanupInterval);
		assertEquals(newCleanup, store.getCleanupInterval());
	}

	@Test
	public void testCleanup() {
		store.setTtl(500); // Entries Only live for .5 seconds
		store.setCleanupInterval(500);

		store.updateStatus(id, newStatus);
		assertEquals(newStatus, store.getStatus(id));

		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			log.warn("interrupted while sleeping");
			throw new RuntimeException("interrupted while sleeping", e);
		}

		assertEquals(newStatus, store.getStatus(id));
		assertEquals(null, store.getStatus(id));
	}
}
