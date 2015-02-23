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

import com.ase.bean.User;
import com.ase.bean.Group;
import com.ase.dao.GroupDAO;


/**
 * Handles requests for the creating a group.
 */
@Controller
public class AddGroupController {
	private static final Logger logger = LoggerFactory.getLogger(AddGroupController.class);
	
	private GroupDAO grpDAO = new GroupDAO();
	
	@RequestMapping(value = "/AddGroup", method = RequestMethod.POST)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//test database object
		String groupName = "nacho";
		String groupId = "12345";
		String groupKey = "abcde";
		
		Group grp = new Group();
		grp.setGroupName(groupName);
		grp.setGroupId(groupId);
		grp.setGroupKey(groupKey);
		
		
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "main";
	}
	
}
