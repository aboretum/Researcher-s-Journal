package com.ase.bean;

import java.util.*;

public class Result_display {
	private String display_id;
	private List<Document> docs;
	private Date submission_date;
	private String category;
	
	public void setDisplayId(String display_id){
		this.display_id = display_id;
	}
	
	public String getDisplayId(){
		return display_id;
	}
	
	public void setDocs(List<Document> docs){
		this.docs = docs;
	}
	
	public List<Document> getDocs(){
		return docs;
	}
	
	public void setSubmission_date(Date date){
		this.submission_date = date;
	}
	
	public Date getSubmission_date(){
		return submission_date;
	}
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public String getCategory(){
		return category;
	}
}
