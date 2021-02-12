package com.cda.jdbc.data;

import java.time.Year;

public class Vehicle {
	private String numberPlate;
	private Year yearProduct;
	private int idModel;
	private int idBrand;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String pNumberPlate, Year pYearProduct,int pIdModel,int pIdBrand) {
		this.numberPlate = pNumberPlate;
		this.yearProduct = pYearProduct;
		this.idModel = pIdModel;
		this.idBrand = pIdBrand;
	}
	
	public String getNumberPlate() {
		return numberPlate;
	}

	public Year getYearProduct() {
		return yearProduct;
	}

	public void setYearProduct(Year yearProduct) {
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