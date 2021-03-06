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

import com.ase.dao.UserDAO;
import com.ase.dao.GroupDAO;
import com.ase.bean.User;
import com.ase.bean.Group;
import com.ase.util.*;



/**
 * Handles requests for the user registration.
 */
@Controller
public class AddUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddUserController.class);
	
	UserDAO userDAO = new UserDAO();
	GroupDAO groupDAO = new GroupDAO();
	
	@RequestMapping(value = "/AddUser", method = RequestMethod.POST)
	public String home(Locale locale, Model model, HttpServletRequest request) throws NoSuchAlgorithmException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//test database object
		String RegUser = request.getParameter("username");
        String RegEmail = request.getParameter("email");
        String RegPass = request.getParameter("password");
        String RegPass2 = request.getParameter("confirmPassword");
        String RegGroup = request.getParameter("groupName");
        String RegGroupKey = request.getParameter("groupKey");
        String member_title = "researcher";
        
		User user = new User();
		
		User dbUser = null;
		
		dbUser = userDAO.getUserByName(RegUser);
		
		
		if(dbUser.getUserName()==null){
			if(RegPass.equals(RegPass2)){
				user.setUserName(RegUser);
				user.setPassWord(RegPass);
				user.setMember_title(member_title);
				user.setUserGroup(RegGroup);
				
				Group userGroup = groupDAO.getGroupByName(RegGroup);
				if(MessageDigestService.getDigest(RegGroupKey).equals(userGroup.getGroupKey())){
					userGroup.addUser(user);
					groupDAO.addNewMember(userGroup.getGroupName(), user);
		
					try {
						userDAO.addUser(user);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
					
					System.out.println("reg complete");
					model.addAttribute("info","Sign up complete");
				}
				
				model.addAttribute("info","Wrong group key");
				
			}else{
				model.addAttribute("info","Password not matching");
				System.out.println("wrong pass");
			}
			
		}else{
			model.addAttribute("info", "User already exists");
			System.out.println("user exists");
		}
		
		//String formattedDate = dateFormat.format(date);
		
		//model.addAttribute("serverTime", formattedDate );
		
		return "login2";
	}
}
