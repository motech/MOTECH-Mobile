package org.motechproject.mobile.imp.serivce.oxd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is an adaptation of Daniel Kayiwa's SecurityUtil class from
 * openxdata server. Here, it is used primarily to encode user passwords for the
 * mobile client.
 * 
 * @author batkinson
 * 
 */
public class PasswordEncoderImpl implements PasswordEncoder {

	public Log log = LogFactory.getLog(PasswordEncoderImpl.class);

	public String encodePassword(String clearPass, String salt) {
		StringBuffer buf = new StringBuffer();
		buf.append(clearPass);
		buf.append(salt);
		return rawToHex(sha1Encode(buf.toString()));
	}

	/**
	 * This method will return a raw sha1 digest of the given string.
	 * 
	 * @param stringToHash
	 *            string to encode
	 * @return the SHA-1 encryption of a given string
	 */
	private byte[] sha1Encode(String stringToHash) {
		try {
			String algorithm = "SHA1";
			MessageDigest md = MessageDigest.getInstance(algorithm);
			return md.digest(stringToHash.getBytes());
		} catch (NoSuchAlgorithmException e) {
			String msg = "Unable to generate sha1 digests";
			log.error(msg, e);
			throw new RuntimeException(msg, e);
		}
	}

	/**
	 * Converts a raw hex in byte[] to an encoded string.
	 * 
	 * @param b
	 *            Byte array to convert to HexString
	 * @return Hexidecimal based string
	 */

	private String rawToHex(byte[] b) {
		if (b == null || b.length < 1)
			return "";
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			s.append(Integer.toHexString(b[i] & 0xFF));
		}
		return s.toString();
	}

	public String generateSalt() {
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		buf.append(Long.toString(System.currentTimeMillis()));
		buf.append(Long.toString(random.nextLong()));
		return rawToHex(sha1Encode(buf.toString()));
	}
}
