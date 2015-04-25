package com.ase.dao;

import com.ase.bean.Group;
import com.ase.bean.User;
import com.ase.util.MessageDigestService;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBList;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class GroupDAO {
	private String dbCol = "groups";
	private DatabaseUtility dbUtil;
	private DBCollection col;
	
	public GroupDAO(){
		dbUtil = DatabaseUtility.getInstance();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}
	
	public void addGroup(Group grp) throws NoSuchAlgorithmException{
		String groupKey = null;
		groupKey = MessageDigestService.getDigest(grp.getGroupKey());
		
		BasicDBObject doc = new BasicDBObject("group_name", grp.getGroupName()).
				append("group_id", grp.getGroupId()).
				append("group_key",groupKey).append("group_users", new BasicDBList()).
				append("group_area", grp.getGroupArea());
		col.insert(doc);
	}
	
	public Group getGroupByName(String grp_name){
		Group grp = new Group();
		BasicDBObject query = new BasicDBObject("group_name", grp_name);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		List<User> grp_users = new ArrayList<User>();
		BasicDBList userList = (BasicDBList) doc.get("group_users");
		for(Object obj :userList){
			DBObject userObj = (DBObject)obj; 
			User user = new User();
			user.setUserName(userObj.get("user_name").toString());
			user.setMember_title(userObj.get("member_title").toString());
			user.setUserGroup(grp_name);
			grp_users.add(user);
		}
		
		grp.setGroupName(doc.get("group_name").toString());
		grp.setGroupId(doc.get("group_id").toString());
		grp.setGroupKey(doc.get("group_key").toString());
		grp.setGroupArea(doc.get("group_area").toString());
		grp.setUsers(grp_users);
		
		return grp;
	}
	
	public void updateGroup(Group grp){
		
	}
	
	public void addNewMember(String grp_name, User user){
		BasicDBObject query = new BasicDBObject("group_name", grp_name);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		//Add new user to the group's user list.
		BasicDBObject push = new BasicDBObject();
		push.put("$push", new BasicDBObject("group_users",new BasicDBObject("user_name", user.getUserName()).
				append("member_title", user.getMember_title()).
				append("user_group", user.getUserGroup())));
		col.update(query, push);
		
	}
	
	public void deleteGroup(String grp_Id){
		
	}
	
}
