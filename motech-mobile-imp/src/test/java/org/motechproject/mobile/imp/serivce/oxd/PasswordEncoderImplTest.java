package org.motechproject.mobile.imp.serivce.oxd;

import junit.framework.TestCase;

/**
 * Tests the {@link PasswordEncoderImpl} class.
 * 
 * @author batkinson
 * 
 */
public class PasswordEncoderImplTest extends TestCase {

	PasswordEncoderImpl encoder;

	@Override
	protected void setUp() throws Exception {
		encoder = new PasswordEncoderImpl();
	}

	@Override
	protected void tearDown() throws Exception {
		encoder = null;

	}

	public void testEncodePassword() {
		String encodedHash = encoder.encodePassword("user", "badsalt");
		assertNotNull(encodedHash);
		assertTrue(encodedHash.matches("[a-fA-F0-9]+"));
		String newHash = encoder.encodePassword("user", "badsalt");
		assertEquals(encodedHash, newHash);
	}

	public void testGenerateSalt() {
		String salt = encoder.generateSalt();
		assertNotNull(salt);
		assertTrue(salt.matches("[a-fA-F0-9]+"));
		String salt2 = encoder.generateSalt();
		assertFalse(salt.equals(salt2));
	}
}
