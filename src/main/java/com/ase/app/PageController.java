package com.ase.app;

import java.util.ArrayList;
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
import com.ase.dao.DatabaseUtility;
import com.ase.dao.DocumentDAO;
import com.ase.dao.GroupDAO;
import com.ase.dao.Result_DisplayDAO;
import com.ase.dao.UserDAO;



/**
 * Handles requests for the page directory.
 */
@Controller
public class PageController {
	
	@Autowired
	ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	private UserDAO userDAO = new UserDAO();
	private GroupDAO groupDAO = new GroupDAO();
	private DocumentDAO DocDAO = new DocumentDAO();
	private Result_DisplayDAO displayDAO = new Result_DisplayDAO();
	
	/**
	 * Display the user group information.
	 */
	@RequestMapping(value = "/GroupInfo", method = RequestMethod.GET)
	public String group(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		
		User user = userDAO.getUserByName(userName);
		Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
		model.addAttribute("userGroup", userGroup);
		model.addAttribute(userGroup.getUsers());
		
		
		return "group-overview";
	}
	
	@RequestMapping(value = "/SingleFileUpload", method = RequestMethod.GET)
	public String singleFile(Locale locale, Model model, HttpServletRequest request){
		logger.info("Welcome home! The client locale is {}.", locale);
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		if(userName!=null){
			User user = userDAO.getUserByName(userName);
			Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
			model.addAttribute("userGroup", userGroup);
			model.addAttribute(userGroup.getUsers());
			
			List<Document> docList = new ArrayList<Document>();
			DocDAO.setServletContext(servletContext);
			docList= DocDAO.getSingleFileDocumentByGroup(userGroup);
			
			if(docList!=null){
				model.addAttribute("files", docList);
			}
		}
		
		
		return "single-file-upload";
	}
	
	/**
	 * Process request for retrieving user's personal page.
	 */
	@RequestMapping(value = "/PersonalPage", method = RequestMethod.GET)
	public String userProfile(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		
		User user = userDAO.getUserByName(userName);
		Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
		
		DocDAO.setServletContext(this.servletContext);
		
		Result_display display = null;
		display = displayDAO.getDisplaybyUser(user);
		List<Document> displayList = new ArrayList<Document>();
		
		if(display.getDocs()!=null){
			for(Document document : display.getDocs()){
				Document newDocument = DocDAO.getDocumentByDateandGroup(document.getDocDate(), userGroup);
				if(newDocument.getDocType()!=null&&newDocument.getDocType().equals(".jpg"))
					displayList.add(0, newDocument);
			}
			display.setDocs(displayList);
		}
		
		model.addAttribute("user", user);
		model.addAttribute("userGroup", userGroup);
		model.addAttribute("display", display);
		
		return "personal-page";
	}
	
	/**
	 * Providing the functionality to toggle the privacy property of a file
	 * from user's personal page.
	 */
	@RequestMapping(value = "/ToggleDocPrivacy", method = RequestMethod.GET)
	public String userDocPrivacy(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		String doc_original_privacy = request.getParameter("doc_original_privacy");
		String doc_private = request.getParameter("doc_privacy");
	
		String docID = request.getParameter("doc_id");
		System.out.println(doc_original_privacy);
		System.out.println(doc_private);
		
		if(!doc_original_privacy.equals(doc_private)){
			DocDAO.toggleDocPrivacy(docID);
		}
		
		User user = userDAO.getUserByName(userName);
		Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
		
		DocDAO.setServletContext(this.servletContext);
		
		Result_display display = null;
		display = displayDAO.getDisplaybyUser(user);
		List<Document> displayList = new ArrayList<Document>();
		
		if(display.getDocs()!=null){
			for(Document document : display.getDocs()){
				Document newDocument = DocDAO.getDocumentByDateandGroup(document.getDocDate(), userGroup);
				displayList.add(0, newDocument);
			}
			display.setDocs(displayList);
		}
		
		model.addAttribute("user", user);
		model.addAttribute("userGroup", userGroup);
		model.addAttribute("display", display);
		
		return "personal-page";
	}
	
}
