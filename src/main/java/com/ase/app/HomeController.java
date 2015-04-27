package com.ase.app;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.ase.bean.Result_display;
import com.ase.bean.User;
import com.ase.dao.DocumentDAO;
import com.ase.dao.GroupDAO;
import com.ase.dao.Result_DisplayDAO;
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
	private Result_DisplayDAO displayDAO = new Result_DisplayDAO();
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		if(userName!=null){
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			String localDate = dateFormat.format(date);
			System.out.println(localDate);
			User user = userDAO.getUserByName(userName);
			Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
			
			model.addAttribute("user", user);
			model.addAttribute("userGroup", userGroup);
			
			Document doc = new Document();
			DocDAO.setServletContext(this.servletContext);
		
			
			Result_display display = null;
			
			display = displayDAO.getDisplaybyDateandGroup(date, userGroup);
			List<Document> displayList = new ArrayList<Document>();
			
			if(display.getDocs()!=null){
				for(Document document : display.getDocs()){
					Document newDocument = DocDAO.getDocumentByDateandGroup(document.getDocDate(), userGroup);
					if(!newDocument.isDocPrivate()){
						if(newDocument.getDocType()!=null&&newDocument.getDocType().equals(".jpg"))
							displayList.add(0, newDocument);
					}
				}
				display.setDocs(displayList);
			}
			
			model.addAttribute("display", display);
			model.addAttribute("doc", doc);
			
			System.out.println(doc.getDocUrl());
		}
		
		return "index";
	}
	
}
