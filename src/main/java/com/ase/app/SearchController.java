package com.ase.app;

import java.text.ParseException;
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
import com.ase.util.DateTimeUnit;



/**
 * Handles requests for the page directory.
 */
@Controller
public class SearchController {
	
	@Autowired
	ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	private UserDAO userDAO = new UserDAO();
	private GroupDAO groupDAO = new GroupDAO();
	private DocumentDAO DocDAO = new DocumentDAO();
	private Result_DisplayDAO displayDAO = new Result_DisplayDAO();
	
	
	@RequestMapping(value = "/SearchDisplay", method = RequestMethod.POST)
	public String fetchDisplayByDate(Locale locale, Model model, HttpServletRequest request) throws ParseException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		String searchDateString = request.getParameter("search_date");
		if(searchDateString.length()==0){
			model.addAttribute("info", "Date can't be empty");
			return "index";
		}
		Date searchDate = DateTimeUnit.getDateByString(searchDateString);
		System.out.println(searchDate);
	
		if(userName!=null){
			
			User user = userDAO.getUserByName(userName);
			Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
			
			model.addAttribute("user", user);
			model.addAttribute("userGroup", userGroup);
			
			Document doc = new Document();
			DocDAO.setServletContext(this.servletContext);
			
			Result_display display = null;
			
			display = displayDAO.searchPreviousDisplaybyDateandGroup(searchDate, userGroup);
			if(display==null){
				model.addAttribute("info","No records for that day");
				System.out.println("No record");
				return "index";
			}
			List<Document> displayList = new ArrayList<Document>();
			
			if(display.getDocs()!=null){
				for(Document document : display.getDocs()){
					Document newDocument = DocDAO.getDocumentByDateandGroup(document.getDocDate(), userGroup);
					if(!newDocument.isDocPrivate()){
						if(newDocument.getDocType()!=null&&(newDocument.getDocType().equals(".jpg")||newDocument.getDocType().equals("textDoc")))
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
	
	@RequestMapping(value="/SearchText", method=RequestMethod.POST)
	public String fetchDocumentsByKeyword(Locale locale, Model model, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		String keyword = request.getParameter("search_text");
		
		if(userName!=null){
			User user = userDAO.getUserByName(userName);
			Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
			
			model.addAttribute("user", user);
			model.addAttribute("userGroup", userGroup);
			
			DocDAO.setServletContext(this.servletContext);
			
			Result_display display = new Result_display();
			List<Document> displayList = DocDAO.searchKeyWordByRegex(keyword, userGroup);
						
			display.setDocs(displayList);
			if(display.getDocs().size()==0){
				model.addAttribute("info","No records found for specific keyword");
				return "index";
			}
			model.addAttribute("display", display);
		}
		
		return "index";
	}
	
}
