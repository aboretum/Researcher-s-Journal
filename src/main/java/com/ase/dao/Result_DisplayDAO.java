package com.ase.dao;


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
import com.ase.bean.Document;
import com.ase.bean.Result_display;

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
	
	public Result_display getDisplaybyDate(Date date){
		Result_display display = new Result_display();
		BasicDBObject query = new BasicDBObject("display_date", date);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		display.setDisplayId(doc.get("display_id").toString());
		display.setCategory(doc.get("display_category").toString());
		display.setDate(doc.get("display_date").toString());
		
		return display;
	}
	
	public void updateResult_display(Result_display display){
		
	}
	
	public void addNewDocument(String displayDate, Document doc){
		
	}
	
	public void deleteResult_display(String display_id){
		
	}
}
