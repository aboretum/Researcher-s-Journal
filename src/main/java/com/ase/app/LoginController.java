package com.ase.app;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ase.dao.UserDAO;
import com.ase.util.MessageDigestService;
import com.ase.bean.User;

/**
 * Handles requests for the application login.
 */
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private UserDAO userDAO = new UserDAO();
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(Locale locale, Model model, HttpServletRequest request) throws NoSuchAlgorithmException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//Date date = new Date();
		//DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		//String formattedDate = dateFormat.format(date);
		
		String loginUser = request.getParameter("username");
        String loginPass = request.getParameter("password");
        
        User user = userDAO.getUserByName(loginUser);
        
        
        if(MessageDigestService.getDigest(loginPass).equals(user.getPassWord())){
        	HttpSession session = request.getSession(true);
        	model.addAttribute(user);
        	session.setAttribute("username", loginUser);
        	session.setAttribute("member_title", user.getMember_Title());
        	System.out.println("You have successfully logged in");
        	
        	
        }else{
        	model.addAttribute("action","wrong pass");
        	return "info";
        }
        
		
		return "main";
	}
	
	
	@RequestMapping(value = "/Signup", method = RequestMethod.GET)
	public String register(Locale locale, Model model, HttpServletRequest request)throws ServletException, IOException {

		return "signup";
	}
}
