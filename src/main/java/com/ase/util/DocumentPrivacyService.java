package com.ase.util;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

public class DocumentPrivacyService {
	
	public static String generateUniqueDocID(){
		String filename="";
	    long millis=System.currentTimeMillis();
	    String datetime=new Date().toString();
	    datetime=datetime.replace(" ", "");
	    datetime=datetime.replace(":", "");
	    String rndchars=RandomStringUtils.randomAlphanumeric(16);
	    filename=rndchars+"_"+datetime+"_"+millis;
	    return filename;
	}
}
