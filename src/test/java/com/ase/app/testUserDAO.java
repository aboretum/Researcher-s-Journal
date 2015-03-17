package com.ase.app;

import com.ase.bean.User;
import com.ase.dao.*;

public class testUserDAO {
	UserDAO userDAO = new UserDAO();
	
	public void testGetUser(){
		User user = userDAO.getUserByName("nacho");
		System.out.println(user.getUserName());
	}
}
