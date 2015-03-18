package com.ase.util;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

public class ServletContextService {
	@Autowired
	private ServletContext servletContext;
	
	public ServletContext getServletContext(){
		return  servletContext;
	}
	
}
