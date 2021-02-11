package com.cda.jdbc.data;

public class Category {
	private int idCategory;
	private String labelCategory;
	
	public Category() {
		
	}
	
	public Category(String pLabelCategory) {
		this.labelCategory=pLabelCategory;
	}	
	
	public Category(int pIdCategory,String pLabelCategory) {
		this.idCategory = pIdCategory;
		this.labelCategory = pLabelCategory;
	}

	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getLabelCategory() {
		return labelCategory;
	}
	public void setLabelCategory(String labelCategory) {
		this.labelCategory = labelCategory;
	}

	public String toString() {
		return "[id=" + idCategory + ", label=" + labelCategory + "]";
	}
}
