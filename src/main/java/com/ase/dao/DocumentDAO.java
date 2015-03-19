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

import javax.servlet.ServletContext;

import org.bson.types.Binary;




public class DocumentDAO {
	private String dbCol = "documents";
	private DatabaseUtility dbUtil;
	private DBCollection col;
	ServletContext servletContext;
	

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
		
		if(doc == null){
			return document;
		}
		
		String docType = doc.get("doc_type").toString();
		
		document.setDocName(doc.get("doc_name").toString());
		document.setDocType(doc.get("doc_type").toString());
		document.setDocContent(doc.get("doc_content").toString());
		
		document.setDocAuthor(doc.get("doc_author").toString());
		document.setDocDate(doc.get("doc_date").toString());
		
		if(docType.equals("figure")){
			byte[] c = (byte[])doc.get("doc_file");
			if(c==null){
				System.out.println("byte is null");
			}
			String fileName = writeToFile(c);
			File file = new File(fileName);
			FigureDocument figureDoc = new FigureDocument();
			figureDoc.setDocName(doc.get("doc_name").toString());
			figureDoc.setDocType(doc.get("doc_type").toString());
			figureDoc.setDocAuthor(doc.get("doc_author").toString());
			figureDoc.setDocDate(doc.get("doc_date").toString());
			
			figureDoc.setDocContent(doc.get("doc_content").toString());
			figureDoc.setImageFile(file);
			figureDoc.setDocUrl(fileName);
			document = (Document)figureDoc;
		}
		return document;
	}
	
	
	public String writeToFile(byte[] data){
		String fileNameBefore = "/resources/images/"+FigureIOService.generateUniqueFileName()+".jpg";
		String fileName = servletContext.getRealPath(fileNameBefore);
		
		try{
			FileOutputStream fout = new FileOutputStream(fileName);
			fout.write(data);
			fout.flush();
			fout.close();
			System.out.println("data written");
		}catch (IOException e) {
            e.printStackTrace();
        }
		
		return fileNameBefore;
	}
	
	public void updateDocument(Document document){
		
	}
	
	public void deleteDocument(String docName){
		
	}
	
	public void setServletContext(ServletContext context){
		this.servletContext = context;
	}
}
