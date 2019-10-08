package fr.triedge.fwk.utils;

import java.util.Base64;

public class SBIEncrypter {
	
	private static final String START					= "sbi-";

	public static String encrypt(String pwd) {
		return START + new String(Base64.getEncoder().encode(pwd.getBytes()));
	}
	
	public static String decode(String pwd) {
		if (!pwd.startsWith(START)) 
			return pwd;
		return new String(Base64.getDecoder().decode(pwd.getBytes()));
	}
}
