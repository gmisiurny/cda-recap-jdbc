package com.cda.jdbc.data;

public class Piece {
	private int idPiece;
	private String label;
	private float price;
	private int idCategory;
	private int idReference;
	
	public Piece() {}
	
	public Piece(String label, float price, int idCategory, int idReference) {
		this.label = label;
		this.idCategory = idCategory;
		this.idReference = idReference;
	}
	
	public Piece(int idPiece, String label, float price, int idCategory, int idReference) {
		this.idPiece = idPiece;
		this.idCategory = idCategory;
		this.idReference = idReference;
	}

	public int getIdPiece() {
		return idPiece;
	}

	public void setIdPiece(int idPiece) {
		this.idPiece = idPiece;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public int getIdReference() {
		return idReference;
	}

	public void setIdReference(int idReference) {
		this.idReference = idReference;
	}
}
