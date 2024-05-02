package com.nimap.MyTask.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;





@Entity
public class products {
	
	@Id
	private int productID;
	
	String productName;
	@ManyToOne
	@JoinColumn(name = "categoryID")
	private categories category;
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "Products [productID=" + productID + ", productName=" + productName + "]";
	}
	public categories getCategory() {
		return category;
	}
	public void setCategory(categories category) {
		this.category = category;
	}
	
	
	

}
