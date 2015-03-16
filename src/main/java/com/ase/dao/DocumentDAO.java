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
import com.ase.util.FigureIOService;

import java.io.*;
import java.util.*;

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
		
		if(document.getDocType().equals("figure")){
			FigureDocument figure = (FigureDocument)document;
			Binary data = FigureIOService.figureTOBinary(figure);

		BasicDBObject doc = new BasicDBObject("doc_name", document.getDocName()).
				append("doc_type", document.getDocType()).
				append("doc_content",document.getDocContent()).
				append("doc_url", document.getDocUrl()).
				append("doc_author", document.getDocAuthor()).
				append("doc_date", new Date()).
				append("doc_file", data);
		
		col.insert(doc);
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
		
		String docType = doc.get("doc_type").toString();
		
		document.setDocName(doc.get("doc_name").toString());
		document.setDocType(doc.get("doc_type").toString());
		document.setDocContent(doc.get("doc_content").toString());
		document.setDocUrl(doc.get("doc_url").toString());
		document.setDocAuthor(doc.get("doc_author").toString());
		document.setDocDate(doc.get("doc_date").toString());
		
		if(docType.equals("figure")){
			byte[] c = (byte[])doc.get("doc_file");
			File file = new File(FigureIOService.writeToFile(c));
			FigureDocument figureDoc = (FigureDocument)document;
			figureDoc.setImageFile(file);
		}
		return document;
	}
	
	public void updateDocument(Document document){
		
	}
	
	public void deleteDocument(String docName){
		
	}
	
}
