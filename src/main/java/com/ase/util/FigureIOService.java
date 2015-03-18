package com.ase.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;

import com.ase.bean.FigureDocument;

public class FigureIOService {
	
	
	public static Binary figureTOBinary(FigureDocument figure){
		Binary data = null;
		try{
			File imageFile = figure.getImageFile();
			FileInputStream fis = new FileInputStream(imageFile);
			byte b[] = new byte[fis.available()];
			fis.read(b);
			data = new Binary(b);
			fis.close();
		}catch(Exception e){
			;
		}
	
		return data;
	}
	
	
	public static String generateUniqueFileName(){
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
