package com.techwl.feedo.utils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class FeedoStringTokensGenerator {

	public static final String generateBase64RandomPassword(String passcode) {
		SecureRandom secureRandom = new SecureRandom();
		byte[] token = new byte[8];
		secureRandom.nextBytes(token);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
	}

	public static final String generateFeedoToken(String... input) {
		StringBuilder finalOutput = new StringBuilder("");
		finalOutput.append(input.length);
		finalOutput.append("F");
		for(String s: input) {
			finalOutput.append(s.length());
			finalOutput.append(s.charAt(0));
		}
		finalOutput.append(input[0].charAt(0));
		finalOutput.append("EE");
		finalOutput.append(input[input.length - 1].charAt(0));
		finalOutput.append("D");
		Date date = new Date();
		finalOutput.append(date.getDay() + "" + date.getMonth() + "" + date.getYear());
		finalOutput.append("O");
		return finalOutput.toString();
	}

}
