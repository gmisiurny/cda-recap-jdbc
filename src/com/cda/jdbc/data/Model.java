package com.cda.jdbc.data;

public class Model {
	private int idModel;
	private String label;
	
	public Model() {}
	
	public Model(String label) {
		this.label = label;
	}
	
	public Model(int id, String label) {
		this.idModel = id;
		this.label = label;
	}

	public int getIdModel() {
		return idModel;
	}

	public void setIdModel(int idModel) {
		this.idModel = idModel;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}	
}
