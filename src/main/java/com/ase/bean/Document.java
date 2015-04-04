package com.ase.bean;

import java.util.*;

public class Document {
	private String docType;
	private String docName;
	private String docAuthor;
	private String docContent;
	private String docDate;
	private String docUrl;
	
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

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	
}
