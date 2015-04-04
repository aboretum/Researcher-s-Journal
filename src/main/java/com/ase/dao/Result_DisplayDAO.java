package com.ase.dao;


import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.ase.bean.Document;
import com.ase.bean.FigureDocument;
import com.ase.bean.Group;
import com.ase.bean.Result_display;
import com.ase.bean.User;

import java.util.*;

public class Result_DisplayDAO {
	private String dbCol = "displays";
	private DatabaseUtility dbUtil;
	private DBCollection col;
	
	public Result_DisplayDAO(){
		dbUtil = new DatabaseUtility();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}
	
	public void addResultDisplay(Result_display result_display){
		BasicDBObject doc = new BasicDBObject("display_id", result_display.getDisplayId()).
				append("display_date", new Date()).
				append("display_category",result_display.getCategory());
		col.insert(doc);
	}
	

	public void addNewDocumentToGroup(Date date, FigureDocument document, Group userGroup) {
		Date startDate = getStartOfDay(new Date());
		Date endDate = getEndOfDay(new Date());
		
		BasicDBObject dateQuery = new BasicDBObject();
		dateQuery.put("display_date", BasicDBObjectBuilder.start("$gte", startDate).add("$lte", endDate).get());
		dateQuery.put("display_group", userGroup.getGroupName());
		
		DBCursor cursor = col.find(dateQuery);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		//Add new document to a specific group's current result display.
		BasicDBObject push = new BasicDBObject();
		push.put("$push", new BasicDBObject("display_docs",new BasicDBObject("doc_author", document.getDocAuthor()).
				append("doc_date", date)));
		col.update(dateQuery, push);
			
	}
	
	public void addNewDocumentToUser(Date date, FigureDocument document, User user){
		BasicDBObject query = new BasicDBObject("display_user", user.getUserName());
		
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		BasicDBObject push = new BasicDBObject();
		push.put("$push", new BasicDBObject("display_docs",new BasicDBObject("doc_author", document.getDocAuthor()).
				append("doc_date", date)));
		col.update(query, push);
		
	}
	
	public Result_display getDisplaybyUser(User user){
		Result_display display = new Result_display();
		BasicDBObject query = new BasicDBObject("display_user", user.getUserName());
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		if(doc==null){
			BasicDBObject rdDoc = new BasicDBObject("display_id", 1).
					append("display_category", "userPersonal").
					append("display_group", user.getUserGroup()).
					append("display_user", user.getUserName());
			col.insert(rdDoc);
			
		}
		
		cursor = col.find(query);
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		
		List<Document> display_docs = new ArrayList<Document>();
		BasicDBList docList = (BasicDBList) doc.get("display_docs");
		
		if(docList!=null){
			for(Object obj : docList){
				DBObject docObj = (DBObject) obj;
				Document d = new Document();
				d.setDocDate((Date)docObj.get("doc_date"));
				d.setDocAuthor(docObj.get("doc_author").toString());
				display_docs.add(d);
			}
			
			display.setDisplayId(doc.get("display_id").toString());
			display.setCategory(doc.get("display_category").toString());
			display.setDisplay_group(doc.get("display_group").toString());
			display.setDocs(display_docs);
		}
		
		return display;
	}
	
	public Result_display getDisplaybyDateandGroup(String date, Group grp){
		Result_display display = new Result_display();
		Date startDate = getStartOfDay(new Date());
		Date endDate = getEndOfDay(new Date());
		
		BasicDBObject dateQuery = new BasicDBObject();
		dateQuery.put("display_date", BasicDBObjectBuilder.start("$gte", startDate).add("$lte", endDate).get());
		dateQuery.put("display_group", grp.getGroupName());
		
		DBCursor rdCursor = col.find(dateQuery);
		DBObject doc = null;
		try{
			while(rdCursor.hasNext()){
				doc=rdCursor.next();
			}
		}finally{
			rdCursor.close();
		}
		
		if(doc==null){
			BasicDBObject rdDoc = new BasicDBObject("display_id", 1).
					append("display_date", new Date()).
					append("display_category",grp.getGroupArea()).
					append("display_group", grp.getGroupName());
			col.insert(rdDoc);
		}
		
		rdCursor = col.find(dateQuery);
		try{
			while(rdCursor.hasNext()){
				doc=rdCursor.next();
			}
		}finally{
			rdCursor.close();
		}
		
		List<Document> display_docs = new ArrayList<Document>();
		BasicDBList docList = (BasicDBList) doc.get("display_docs");
		
		if(docList!=null){
			for(Object obj : docList){
				DBObject docObj = (DBObject) obj;
				Document d = new Document();
				d.setDocDate((Date)docObj.get("doc_date"));
				d.setDocAuthor(docObj.get("doc_author").toString());
				display_docs.add(d);
			}
			
			display.setDisplayId(doc.get("display_id").toString());
			display.setCategory(doc.get("display_category").toString());
			display.setDate(doc.get("display_date").toString());
			display.setDocs(display_docs);
		}
		
		
		
		return display;
	}
	
	public void updateResult_display(Result_display display){
		
	}
	
	public void addNewDocument(String displayDate, Document doc){
		
	}
	
	public void deleteResult_display(String display_id){
		
	}
	
	public Date getEndOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    return calendar.getTime();
	}

	public Date getStartOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}

}
