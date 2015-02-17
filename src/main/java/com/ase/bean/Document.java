package com.ase.bean;

public class Document {
	private String docType;
	private String docName;
	private String docAuthor;
	private String docContent;
	
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
	
	
	
}
