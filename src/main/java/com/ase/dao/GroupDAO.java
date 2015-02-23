package com.ase.dao;

import com.ase.bean.Group;
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

public class GroupDAO {
	private String dbCol = "groups";
	private DatabaseUtility dbUtil;
	private DBCollection col;
	
	public GroupDAO(){
		dbUtil = new DatabaseUtility();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}
	
	public void addGroup(Group grp){
		
		BasicDBObject doc = new BasicDBObject("group_name", grp.getGroupName()).
				append("group_id", grp.getGroupId()).
				append("group_key",grp.getGroupKey());
		col.insert(doc);
	}
	
	public Group getGroupById(String grp_Id){
		Group grp = new Group();
		BasicDBObject query = new BasicDBObject("group_id", grp_Id);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		grp.setGroupName(doc.get("group_name").toString());
		grp.setGroupId(doc.get("group_id").toString());
		grp.setGroupKey(doc.get("group_key").toString());
		return grp;
	}
	
	public void updateGroup(Group grp){
		
	}
	
	public void deleteGroup(String grp_Id){
		
	}
	
}
