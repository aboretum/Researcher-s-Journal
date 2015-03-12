package com.ase.bean;

public class User {
	private String userName;
	private String passWord;
	private String member_title;
	private String userGroup;
	
	public User(){
		
	}
		
	public String getUserName(){
		
		return userName;
	}
	
	public void setUserName(String userName){
		
		this.userName = userName;
	}
	
	public void setPassWord(String passWord){
		
		this.passWord = passWord;
	}
	
	public String getPassWord(){
		
		return passWord;
	}
	
	public String getMember_Title(){
		
		return member_title;
	}
	
	public void setMember_Title(String member_title){
		
		this.member_title = member_title;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	
}
