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
	String DBName, DBUser, DBPass, SQLQuery;
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
    
    public DBCollection getCollection(){
    	DBCollection col = db.getCollection("testData");
    	return col;
    }
    
    
    
    public ResultSet selectQuery(String SQLQuery){
    	this.SQLQuery = SQLQuery;
    	
        try {
        	stmt = conn.createStatement();
            result = stmt.executeQuery( SQLQuery );
        }
        catch( Exception e ){}
    	return result;
    }
    
    public void query(String SQLQuery){
    	this.SQLQuery = SQLQuery;
        try {
        	stmt = conn.createStatement();
            stmt.executeUpdate(SQLQuery);
        }
        catch( Exception e ){}
    }
    
    public void close(){
    	try {
        	stmt.close();
            conn.close();
        }
        catch(Exception e){}
    }
    
    
}
