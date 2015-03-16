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
import com.ase.bean.FigureDocument;

import java.io.*;

import org.bson.types.Binary;

public class DocumentDAO {
	private String dbCol = "documents";
	private DatabaseUtility dbUtil;
	private DBCollection col;
	
	public DocumentDAO(){
		dbUtil = new DatabaseUtility();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}
	
	public void addDocument(Document document){
		try{
		FigureDocument figure = (FigureDocument)document;
		File imageFile = figure.getImageFile();
		FileInputStream fis = new FileInputStream(imageFile);
		byte b[] = new byte[fis.available()];
		fis.read();
		Binary data = new Binary(b);
		
		BasicDBObject doc = new BasicDBObject("doc_name", document.getDocName()).
				append("doc_type", document.getDocType()).
				append("doc_content",document.getDocContent()).
				append("doc_url", document.getDocUrl()).
				append("doc_author", document.getDocAuthor()).
				append("doc_file", data);
		
		col.insert(doc);
		fis.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Document getDocumentByName(String docName){
		Document document = new Document();
		BasicDBObject query = new BasicDBObject("doc_name", docName);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		document.setDocName(doc.get("doc_name").toString());
		document.setDocType(doc.get("doc_type").toString());
		document.setDocContent(doc.get("doc_content").toString());
		document.setDocUrl(doc.get("doc_url").toString());
		document.setDocAuthor(doc.get("doc_author").toString());
		return document;
	}
	
	public void updateDocument(Document document){
		
	}
	
	public void deleteDocument(String docName){
		
	}
	
}
