package com.ase.dao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
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


public class DatabaseUtility {

    Connection conn;
    Statement stmt;
    ResultSet result;
    MongoClient mongoClient;
    DB db;
    DBCollection coll;
    
    public DatabaseUtility(){
    	
    	try{
    		mongoClient = new MongoClient("localhost", 27017);
    	}catch(Exception e){
    		e.printStackTrace();
    	}    	
    }
    
    public void connect() {
    	try {
    		db = mongoClient.getDB("RJdb");
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    public DBCollection getCollection(String collection){
    	DBCollection col = db.getCollection(collection);
    	return col;
    }
    
    
}
