package com.ase.bean;

import java.util.*;

public class Document {
	private String docType;
	private String docName;
	private String docID;
	private String docAuthor;
	private String docContent;
	private Date docDate;
	private String docUrl;
	private boolean docPrivate;
	
	public void setDocType(String docType){
		this.docType = docType;
	}
	
	public String getDocType(){
		return this.docType;
	}
	
	public void setDocName(String docName){
		this.docName = docName;
	}
	
	public String getDocName(){
		return this.docName;
	}
	
	public void setDocAuthor(String docAuthor){
		this.docAuthor = docAuthor;
	}
	
	public String getDocAuthor(){
		return this.docAuthor;
	}
	
	public void setDocContent(String docContent){
		this.docContent = docContent;
	}
	
	public String getDocContent(){
		return this.docContent;
	}
	
	public void setDocUrl(String docUrl){
		this.docUrl = docUrl;
	}
	
	public String getDocUrl(){
		return docUrl;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public boolean isDocPrivate() {
		return docPrivate;
	}

	public void setDocPrivate(boolean docPrivate) {
		this.docPrivate = docPrivate;
	}

	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}
	
}
