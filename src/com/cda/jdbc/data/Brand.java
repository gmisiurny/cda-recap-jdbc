package com.cda.jdbc.data;

public class Brand {
	private int idBrand;
	private String label;
	
	public Brand() {
		
	}
	
	public Brand(String label) {
		this.label = label;
	}
	
	public Brand(int id, String label) {
		this.idBrand = id;
		this.label = label;
	}

	public int getId() {
		return idBrand;
	}

	public void setId(int id) {
		this.idBrand = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String toString() {
		return "Id: " + this.idBrand + "\nLabel: " + this.label;
	}
}
