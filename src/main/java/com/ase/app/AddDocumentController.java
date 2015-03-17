package com.ase.app;

import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import com.ase.bean.Document;
import com.ase.bean.FigureDocument;
import com.ase.bean.Group;
import com.ase.bean.User;
import com.ase.dao.DocumentDAO;
import com.ase.util.MessageDigestService;

import java.io.*;

@Controller
public class AddDocumentController implements ServletContextAware {
	private static final Logger logger = LoggerFactory.getLogger(AddDocumentController.class);
	
	@Autowired
	ServletContext servletContext;
	
	DocumentDAO docDAO = new DocumentDAO();
	
	@RequestMapping(value = "/AddDocument", method = RequestMethod.POST)
	public String addDoc(Locale locale, Model model, HttpServletRequest request)  {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//Date date = new Date();
		//DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		//test database transferring object
		
        String docType = "figure";
        
        if(docType.equals("figure")){
     
        	String fileName = "/resources/images/background.jpg";
        	
        	FigureDocument doc = new FigureDocument();
        	doc.setDocAuthor("chouchou");
        	doc.setDocContent("nocontent");
        	doc.setDocName("springData");
        	doc.setDocType("figure");
        	doc.setDocUrl("foo.com");
        	if(servletContext == null){
        		System.out.println("no such servlet");
        	}
            File image = new File(servletContext.getRealPath(fileName));
            doc.setImageFile(image);
            docDAO.addDocument(doc);
        }
        
        
        
		return "home";
	}

	@Override
	public void setServletContext(ServletContext ctx) {
		this.servletContext = ctx;		
	}
	
	
}
