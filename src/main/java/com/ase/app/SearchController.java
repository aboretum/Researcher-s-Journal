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
import com.ase.dao.DocumentDAO;
import com.ase.dao.GroupDAO;
import com.ase.dao.Result_DisplayDAO;
import com.ase.dao.UserDAO;



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
	public String userDocPrivacy(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		String searchDateString = request.getParameter("search_date");
		System.out.println(searchDateString);
		
		return "index";
	}
	
}
