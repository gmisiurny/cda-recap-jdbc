package com.cda.jdbc.data;

public class Reference {
	private int idRef;
	private String reference;
	private int quantity;
	
	public Reference() {}
	
	public Reference(String reference) {
		this.reference = reference;
	}
	
	public Reference(String reference, int quantity) {
		this.reference = reference;
		this.quantity = quantity;
	}
	
	public Reference(int id, String reference, int quantity) {
		this.idRef = id;
		this.reference = reference;
		this.quantity = quantity;
	}

	public int getIdRef() {
		return this.idRef;
	}

	public void setIdRef(int idRef) {
		this.idRef = idRef;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
