package com.ase.bean;

import java.util.*;

public class Group {
	private String groupName;
	private String groupId;
	private String groupKey;
	private String groupArea;
	private int numUsers;
	private List<User> users; 
	
	public Group(){
		users = new ArrayList<User>();
	}
	
	public void setGroupName(String grp_name){
		this.groupName = grp_name;
	}
	
	public String getGroupName(){
		if(groupName!=null){
			return groupName;
		}
		return groupName;
	}
	
	public void setGroupId(String grp_Id){
		this.groupId = grp_Id;
	}
	
	public String getGroupId(){
		if(groupId!=null){
			return groupId;
		}
		return groupId;
	}
	
	public void setGroupKey(String grp_key){
		this.groupKey = grp_key;
	}
	
	public String getGroupKey(){
		return groupKey;
	}
	
	public void addUser(User user){
		users.add(user);
	}
	public int getNumUsers(){
		return users.size();
	}
	
	public void setUsers(List<User> grp_users){
		this.users = grp_users;
	}
	
	public List<User> getUsers(){
		return this.users;
	}

	public String getGroupArea() {
		return groupArea;
	}

	public void setGroupArea(String groupArea) {
		this.groupArea = groupArea;
	}
	
}
