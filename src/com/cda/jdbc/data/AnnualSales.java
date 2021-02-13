package com.cda.jdbc.data;

public class AnnualSales {
	float sales;
	int year;
	
	public AnnualSales(int pYear,float pSales) {
		this.sales=pSales;
		this.year=pYear;
	}

	public float getSales() {
		return sales;
	}

	public void setSales(float sales) {
		this.sales = sales;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String toString() {
		return "[Chiffre d'affaire: " + this.sales + "]  [Année: " + this.year+ "]";
	}
}
