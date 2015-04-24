package com.ase.dao;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.common.MongoServiceInfo;

import java.sql.*;
import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;


public class DatabaseUtility {

    Connection conn;
    Statement stmt;
    ResultSet result;
    MongoClient mongoClient;
    DB db;
    DBCollection coll;
    //CloudFactory cloudFactory = new CloudFactory();
	//Cloud cloud = cloudFactory.getCloud();
	//MongoServiceInfo srvInfo = (MongoServiceInfo) cloud.getServiceInfo("mongo02");
	
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
    		//db = mongoClient.getDB(srvInfo.getDatabase());  //For Bluemix Deployment;
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    public DBCollection getCollection(String collection){
    	DBCollection col = db.getCollection(collection);
    	return col;
    }
    
    /**
     * For IBM Bluemix Deployment
     * MongoCredential credential = MongoCredential.createCredential(srvInfo.getUserName(), srvInfo.getDatabase(), srvInfo.getPassword().toCharArray());
       mongoClient = new MongoClient(new ServerAddress(srvInfo.getHost(), srvInfo.getPort()),Arrays.asList(credential));
     */
}
