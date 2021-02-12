package com.cda.jdbc.data;


public class Vehicle {
	private String numberPlate;
	private int yearProduct;
	private int idModel;
	private int idBrand;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String pNumberPlate, int pYearProduct,int pIdModel,int pIdBrand) {
		this.numberPlate = pNumberPlate;
		this.yearProduct = pYearProduct;
		this.idModel = pIdModel;
		this.idBrand = pIdBrand;
	}
	
	public String getNumberPlate() {
		return numberPlate;
	}

	public int getYearProduct() {
		return yearProduct;
	}

	public void setYearProduct(int yearProduct) {
		this.yearProduct = yearProduct;
	}

	public int getIdModel() {
		return idModel;
	}

	public void setIdModel(int idModel) {
		this.idModel = idModel;
	}

	public int getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(int idBrand) {
		this.idBrand = idBrand;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
}
