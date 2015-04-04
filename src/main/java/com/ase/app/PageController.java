package com.ase.app;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ase.bean.Document;
import com.ase.bean.Group;
import com.ase.bean.User;
import com.ase.dao.DatabaseUtility;
import com.ase.dao.GroupDAO;
import com.ase.dao.UserDAO;



/**
 * Handles requests for the page directory.
 */
@Controller
public class PageController {
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	private UserDAO userDAO = new UserDAO();
	private GroupDAO groupDAO = new GroupDAO();
	
	/**
	 * Display the user group information.
	 */
	@RequestMapping(value = "/GroupInfo", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		
		User user = userDAO.getUserByName(userName);
		Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
		model.addAttribute("userGroup", userGroup);
		model.addAttribute(userGroup.getUsers());
		
		
		return "group-overview";
	}
	
	@RequestMapping(value = "/PersonalPage", method = RequestMethod.GET)
	public String userProfile(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		
		User user = userDAO.getUserByName(userName);
		Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
		model.addAttribute("userGroup", userGroup);
		model.addAttribute(userGroup.getUsers());
		
		
		return "personal-page";
	}
	
}
