package com.ase.app;

import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ase.bean.Document;
import com.ase.bean.FigureDocument;
import com.ase.bean.Group;
import com.ase.bean.User;
import com.ase.dao.DocumentDAO;
import com.ase.util.MessageDigestService;
import java.io.*;

public class AddDocumentController {
	private static final Logger logger = LoggerFactory.getLogger(AddDocumentController.class);
	
	DocumentDAO docDAO = new DocumentDAO();
	
	@RequestMapping(value = "/AddDocument", method = RequestMethod.POST)
	public String addDoc(Locale locale, Model model, HttpServletRequest request)  {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//Date date = new Date();
		//DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		//test database object
		String RegUser = request.getParameter("username");
        String RegEmail = request.getParameter("email");
        String RegPass = request.getParameter("password");
        String RegPass2 = request.getParameter("confirmPassword");
        String RegGroup = request.getParameter("groupName");
        String RegGroupKey = request.getParameter("groupKey");
        String member_title = "researcher";
        String docType = null;
        
        if(docType.equals("figure")){
        	String fileName = "/resources/images/background";
        	FigureDocument doc = new FigureDocument();
            File image = new File(fileName);
            doc.setImageFile(image);
            docDAO.addDocument(doc);
        }
        
		return "home";
	}
	
	
}
