package com.ase.bean;

import java.util.*;

public class Result_display {
	private String display_id;
	private List<Document> docs;
	private String date;
	private String category;
	private String display_group;
	
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
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public String getCategory(){
		return category;
	}

	public String getDisplay_group() {
		return display_group;
	}

	public void setDisplay_group(String display_group) {
		this.display_group = display_group;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
