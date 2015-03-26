package com.ase.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ase.bean.Result_display;
import com.ase.dao.Result_DisplayDAO;


@Controller
public class AddResultDisplayController {
	
	private Result_DisplayDAO displayDAO = new Result_DisplayDAO();
	
	@RequestMapping(value = "/AddGroup", method = RequestMethod.POST)
	public void addDisplay(Locale locale, Model model, HttpServletRequest request){
		Result_display display = new Result_display();
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String localDate = dateFormat.format(date);
		display.setDate(localDate);
		
		
		
	}
}
