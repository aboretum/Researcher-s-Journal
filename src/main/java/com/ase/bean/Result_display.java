package com.ase.bean;

import java.util.*;

public class Result_display {
	private List<Document> docs;
	private Date submission_date;
	private String category;
	
	private void setDocs(List<Document> docs){
		this.docs = docs;
	}
	
	private List<Document> getDocs(){
		return docs;
	}
	
	private void setSubmission_date(Date date){
		this.submission_date = date;
	}
	
	private Date getSubmission_date(){
		return submission_date;
	}
	
	private void setCategory(String category){
		this.category = category;
	}
	
	private String getCategory(){
		return category;
	}
}
