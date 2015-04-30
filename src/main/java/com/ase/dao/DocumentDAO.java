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
import com.ase.bean.Group;
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
		dbUtil = DatabaseUtility.getInstance();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}
	
	public void addDocument(Document document, Date date){
		FigureDocument figure = (FigureDocument)document;
		Binary data = FigureIOService.figureTOBinary(figure);

		BasicDBObject doc = new BasicDBObject("doc_name", document.getDocName()).
				append("doc_type", document.getDocType()).
				append("doc_content",document.getDocContent()).
				append("doc_url", document.getDocUrl()).
				append("doc_author", document.getDocAuthor()).
				append("doc_date", date).
				append("doc_file", data).
				append("doc_id", document.getDocID()).
				append("doc_private", false).
				append("doc_group", document.getDocGroup());
		
		col.insert(doc);
	}
	
	public Document getDocumentByDateandGroup(Date date, Group grp){
		Document document = new Document();
		BasicDBObject query = new BasicDBObject("doc_date", date);
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
		document.setDocID(doc.get("doc_id").toString());
		document.setDocAuthor(doc.get("doc_author").toString());
		document.setDocDate((Date)doc.get("doc_date"));
		document.setDocPrivate((Boolean)doc.get("doc_private"));
		
		
		if(docType.equals(".jpg")){
			byte[] c = (byte[])doc.get("doc_file");
			if(c==null){
				System.out.println("byte is null");
			}
			String fileName = writeToFile(c, docType);
			File file = new File(fileName);
			FigureDocument figureDoc = new FigureDocument();
			figureDoc.setDocName(doc.get("doc_name").toString());
			figureDoc.setDocType(doc.get("doc_type").toString());
			figureDoc.setDocAuthor(doc.get("doc_author").toString());
			figureDoc.setDocDate((Date)doc.get("doc_date"));
			figureDoc.setDocPrivate((Boolean)doc.get("doc_private"));
			
			figureDoc.setDocContent(doc.get("doc_content").toString());
			figureDoc.setDocID(doc.get("doc_id").toString());
			figureDoc.setImageFile(file);
			figureDoc.setDocUrl(fileName);
			document = (Document)figureDoc;
		}
		
		if(docType.equals(".pdf")){
			byte[] c = (byte[])doc.get("doc_file");
			if(c==null){
				System.out.println("byte is null");
			}
			String fileName = writeToFile(c, docType);
			File file = new File(fileName);
			FigureDocument figureDoc = new FigureDocument();
			figureDoc.setDocName(doc.get("doc_name").toString());
			figureDoc.setDocType(doc.get("doc_type").toString());
			figureDoc.setDocAuthor(doc.get("doc_author").toString());
			figureDoc.setDocDate((Date)doc.get("doc_date"));
			figureDoc.setDocPrivate((Boolean)doc.get("doc_private"));
			
			figureDoc.setDocContent(doc.get("doc_content").toString());
			figureDoc.setDocID(doc.get("doc_id").toString());
			figureDoc.setImageFile(file);
			figureDoc.setDocUrl(fileName);
			document = (Document)figureDoc;
		}
		return document;
	}
	
	
	public String writeToFile(byte[] data, String docType){
		String fileNameBefore = "/resources/images/"+FigureIOService.generateUniqueFileName()+docType;
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

	public void toggleDocPrivacy(String docID) {
		BasicDBObject query = new BasicDBObject("doc_id", docID);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		Boolean doc_privacy = (Boolean)doc.get("doc_private");
		
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.put("$set", 
			new BasicDBObject().append("doc_private", !doc_privacy));
		col.update(query, updateQuery);
	}

	public List<Document> searchKeyWordByRegex(String keyword, Group userGroup) {
		List<Document> docList= new ArrayList<Document>();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("doc_name", new BasicDBObject("$regex", keyword).append("$options", "i")));
		obj.add(new BasicDBObject("doc_content",new BasicDBObject("$regex", keyword).append("$options", "i")));
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put("doc_group", userGroup.getGroupName());
		regexQuery.put("$or",obj);
		
		DBCursor cursor = col.find(regexQuery);
		DBObject doc = null;
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
				if(doc.get("doc_type").toString().equals(".jpg")){
					Document document = null;
					byte[] c = (byte[])doc.get("doc_file");
					if(c==null){
						System.out.println("byte is null");
					}
					String fileName = writeToFile(c, ".jpg");
					File file = new File(fileName);
					FigureDocument figureDoc = new FigureDocument();
					figureDoc.setDocName(doc.get("doc_name").toString());
					figureDoc.setDocType(doc.get("doc_type").toString());
					figureDoc.setDocAuthor(doc.get("doc_author").toString());
					figureDoc.setDocDate((Date)doc.get("doc_date"));
					figureDoc.setDocPrivate((Boolean)doc.get("doc_private"));
					figureDoc.setDocContent(doc.get("doc_content").toString());
					figureDoc.setDocID(doc.get("doc_id").toString());
					figureDoc.setImageFile(file);
					figureDoc.setDocUrl(fileName);
					document = (Document)figureDoc;
					docList.add(document);
				}
				
			}
		}finally{
			cursor.close();
		}
		
		return docList;
	}

	public Document getDocumentByGroup(Group userGroup) {
		Document document = new Document();
		
		
		
		
		return document;
	}

	public List<Document> getSingleFileDocumentByGroup(Group userGroup) {
		List<Document> docList = new ArrayList<Document>();
		BasicDBObject query = new BasicDBObject();
		query.put("doc_group", userGroup.getGroupName());
		
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		try{
			while(cursor.hasNext()){
				Document document;
				doc = cursor.next();
				String docType = doc.get("doc_type").toString();
				if(docType.equals(".pdf")||docType.equals(".docx")||
						docType.equals(".doc")||docType.equals(".xlsx")||docType.equals(".xls")){
					byte[] c = (byte[])doc.get("doc_file");
					if(c==null){
						System.out.println("byte is null");
					}
					String fileName = writeToFile(c, docType);
					File file = new File(fileName);
					FigureDocument figureDoc = new FigureDocument();
					figureDoc.setDocName(doc.get("doc_name").toString());
					figureDoc.setDocType(doc.get("doc_type").toString());
					figureDoc.setDocAuthor(doc.get("doc_author").toString());
					figureDoc.setDocDate((Date)doc.get("doc_date"));
					figureDoc.setDocPrivate((Boolean)doc.get("doc_private"));
					figureDoc.setDocContent(doc.get("doc_content").toString());
					figureDoc.setDocID(doc.get("doc_id").toString());
					figureDoc.setImageFile(file);
					figureDoc.setDocUrl(fileName);
					document = (Document)figureDoc;
					docList.add(document);
				}
			}
		}finally{
			cursor.close();
		}
		
		return docList;
		
	}

	public List<Document> getResultFigureDocumentByGroup(Group userGroup) {
		List<Document> docList = new ArrayList<Document>();
		BasicDBObject query = new BasicDBObject();
		query.put("doc_group", userGroup.getGroupName());
		
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		try{
			while(cursor.hasNext()){
				Document document;
				doc = cursor.next();
				String docType = doc.get("doc_type").toString();
				if(docType.equals(".jpg")){
					byte[] c = (byte[])doc.get("doc_file");
					if(c==null){
						System.out.println("byte is null");
					}
					String fileName = writeToFile(c, docType);
					File file = new File(fileName);
					FigureDocument figureDoc = new FigureDocument();
					figureDoc.setDocName(doc.get("doc_name").toString());
					figureDoc.setDocType(doc.get("doc_type").toString());
					figureDoc.setDocAuthor(doc.get("doc_author").toString());
					figureDoc.setDocDate((Date)doc.get("doc_date"));
					figureDoc.setDocPrivate((Boolean)doc.get("doc_private"));
					figureDoc.setDocContent(doc.get("doc_content").toString());
					figureDoc.setDocID(doc.get("doc_id").toString());
					figureDoc.setImageFile(file);
					figureDoc.setDocUrl(fileName);
					document = (Document)figureDoc;
					docList.add(document);
				}
			}
		}finally{
			cursor.close();
		}
		
		return docList;
	}

}
