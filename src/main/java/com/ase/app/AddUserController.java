package com.ase.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ase.dao.UserDAO;
import com.ase.bean.User;



/**
 * Handles requests for the user registration.
 */
@Controller
public class AddUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddUserController.class);
	
	UserDAO userDAO = new UserDAO();
	
	@RequestMapping(value = "/AddUser", method = RequestMethod.POST)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//test database object
		String userName = "nacho";
		String passWord = "123";
		String member_title = "researcher";
		
		User user = new User();
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setMember_Title(member_title);
		
		userDAO.addUser(user);
		
		String formattedDate = dateFormat.format(date);
		
		User newUser = userDAO.getUserByName("nacho");
		model.addAttribute(newUser);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
}
