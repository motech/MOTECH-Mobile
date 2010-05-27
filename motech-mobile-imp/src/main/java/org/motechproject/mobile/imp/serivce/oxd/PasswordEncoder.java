package org.motechproject.mobile.imp.serivce.oxd;

public interface PasswordEncoder {

	/**
	 * Encodes the specified clear text password and returns the SHA1 hash.
	 * 
	 * @param clearPass
	 *            the password to encode
	 * @param salt
	 *            the salt to encode with
	 * @return the hash of the password
	 */
	public abstract String encodePassword(String clearPass, String salt);

	/**
	 * Generate a random string to use as salt for hashing a password digest.
	 * 
	 * @return a secure random token.
	 */
	public abstract String generateSalt();

}
