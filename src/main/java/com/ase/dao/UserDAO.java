package com.ase.dao;

import com.ase.bean.User;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import java.util.*;

public class UserDAO {
	
	private String dbCol = "users";
	private DatabaseUtility dbUtil;
	private DBCollection col;
	
	public UserDAO(){
		dbUtil = new DatabaseUtility();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}
	
	public void addUser(User user){
		
		int hashPass = user.getPassWord().hashCode();
		
		BasicDBObject doc = new BasicDBObject("user_name",user.getUserName()).
				append("password",user.getPassWord()).
				append("member_title",user.getMember_Title());
		col.insert(doc);
		
	}
	
	public User getUserByName(String userName){
		User user = new User();
		BasicDBObject query = new BasicDBObject("user_name", userName);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		String x = doc.get("user_name").toString();
		System.out.println(x);
		/*
		user.setUserName((String)doc.get("user_name"));
		user.setPassWord((String)doc.get("password"));
		user.setMember_Title((String)doc.get("member_title"));
		*/
		
		return user;
	}
	
	public List<User> getAllUsers(){
		
		return null;
	}
	
	public void updateUser(User user){
		String userName = user.getUserName();
		BasicDBObject query = new BasicDBObject("user_name", userName);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		doc.put("password", user.getPassWord());
		doc.put("member_title", user.getMember_Title());
		
		
	}
	
	public void deleteUser(String userName){
		
	}
	
	
}