package com.ase.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestService {
	private static MessageDigest md;
	
	public static String getDigest(String text) throws NoSuchAlgorithmException{
		md = MessageDigest.getInstance("MD5");
		md.update(text.getBytes());
		byte[] result_byte = md.digest();
		String result = new String(result_byte);
		return result;
	}
}
