package com.ase.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class GarbageCollector {
	private static final double CHECK_INTERVAL = 500000;
	
	public void deleteFiles(){
		File file = new File("/app/resources/images");
		deleteFiles(file);
	}
	
	public void deleteFiles(File file){
		if(file.isDirectory()){
			for(File f: file.listFiles()){
				long diff = new Date().getTime()-file.lastModified();
				if(diff>60*60*1000)
					deleteFiles(f);
			}
		}else{
			file.delete();
		}
	}
	
}
