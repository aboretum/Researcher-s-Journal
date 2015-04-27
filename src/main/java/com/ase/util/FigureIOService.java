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
	
	public static String generateFileExtension(String docType){
		String resType = "";
		if(docType.equals("image/jpeg")){
			resType = ".jpg";
		}else if(docType.equals("application/pdf")){
			resType = ".pdf";
		}else if(docType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
			resType = ".docx";
		}else if(docType.equals("application/msword")){
			resType = ".doc";
		}else if(docType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
			resType = ".xlsx";
		}else if(docType.equals("application/vnd.ms-excel")){
			resType = ".xls";
		}else{
			resType = "no support";
		}
		return resType;
	}
}
