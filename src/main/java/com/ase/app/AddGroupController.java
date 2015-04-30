package com.ase.app;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String addGroup(Locale locale, Model model, HttpServletRequest request) throws NoSuchAlgorithmException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//test database object
		//test database object
		String groupName = request.getParameter("group_name");
		String groupId = "12345";
		String groupKey = request.getParameter("group_key");
		String groupKeyConfirm = request.getParameter("confirm_group_key");
		String area_of_interest = request.getParameter("field_study");;
		
		if(!groupKey.equals(groupKeyConfirm)){
			model.addAttribute("info","Group Key doesn't match");
			return "login2";
		}
		
		Group grp = new Group();
		grp.setGroupName(groupName);
		grp.setGroupId(groupId);
		grp.setGroupKey(groupKey);
		grp.setGroupArea(area_of_interest);
		grpDAO.addGroup(grp);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("info", "Group created: "+groupName);
		
		return "login2";
	}
	
}
