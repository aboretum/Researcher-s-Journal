package com.ase.app;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RetrievePassController {

	private static final Logger logger = LoggerFactory.getLogger(RetrievePassController.class);
	
	@RequestMapping(value = "/SendPassWord", method = RequestMethod.POST)
	public String addDoc(Locale locale, Model model, HttpServletRequest request, 
			@RequestParam(value="email")String email,
			@RequestParam(value="username") String userName) {
		
		
		model.addAttribute("info", "Email sent");
		return "index";
	
}

	
}