package com.ase.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;



public class GarbageCollector implements ServletContextAware{
	@Autowired
	ServletContext servletContext;
	
	private static final double CHECK_INTERVAL = 100000;
	
	public void deleteFiles(){
		File file = new File("/app/resources/images");
		deleteFiles(file);
	}
	
	public void deleteFiles(File file){
		if(file.isDirectory()){
			for(File f: file.listFiles()){
				long diff = new Date().getTime()-f.lastModified();
				
				if(diff>CHECK_INTERVAL){
					System.out.println("Deleted "+f.getName());
					deleteFiles(f);
				}else{
					System.out.println("Checked, nothing to delete");
				}
					
			}
		}else{
			file.delete();
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
}

