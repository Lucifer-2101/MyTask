package com.nimap.MyTask.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class categories {
	
	@Id
	int categoryID;
	String categoryName;
	
	 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	   private List<products> product;

	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "categories [categoryID=" + categoryID + ", categoryName=" + categoryName + "]";
	}
	
}
