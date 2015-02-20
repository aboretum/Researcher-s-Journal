package com.ase.dao;

import com.ase.bean.User;

public class UserDAO {
	private DatabaseUtility dbUtil;
	
	public UserDAO(){
		dbUtil = new DatabaseUtility();
		dbUtil.connect();
	}
	
	public void addUser(User user){
		
		
	}
	
	public void updateUser(User user){
		
		
	}

}
