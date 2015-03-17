package com.ase.app;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LogoutController {
	
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String index(Locale locale, Model model, HttpServletRequest request) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(true);
			session.removeAttribute("username");
			session.removeAttribute("password");
			session.removeAttribute("member_title");
			
			return "index";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "null";
	}
}
