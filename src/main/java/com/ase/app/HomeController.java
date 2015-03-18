package com.ase.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ase.bean.Document;
import com.ase.bean.Group;
import com.ase.bean.User;
import com.ase.dao.DocumentDAO;
import com.ase.dao.GroupDAO;
import com.ase.dao.UserDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private UserDAO userDAO = new UserDAO();
	private GroupDAO groupDAO = new GroupDAO();
	private DocumentDAO DocDAO = new DocumentDAO();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		if(userName!=null){
			User user = userDAO.getUserByName(userName);
			Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
			
			model.addAttribute("user", user);
			model.addAttribute("userGroup", userGroup);
			
			Document doc = new Document();
			DocDAO.setServletContext(this.servletContext);
			doc = DocDAO.getDocumentByName("springData2");
			
			model.addAttribute("doc", doc);
			
			System.out.println(doc.getDocUrl());
		}
		
		
		
		return "index";
	}
	
}
