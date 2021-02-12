package com.cda.jdbc.data;

public class Reference {
	private int idRef;
	private String reference;
	
	public Reference() {}
	
	public Reference(String reference) {
		this.reference = reference;
	}
	
	public Reference(int id, String reference) {
		this.idRef = id;
		this.reference = reference;
	}

	public int getIdRef() {
		return idRef;
	}

	public void setIdRef(int idRef) {
		this.idRef = idRef;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
}
