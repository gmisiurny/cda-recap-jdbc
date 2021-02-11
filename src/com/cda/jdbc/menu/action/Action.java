package com.cda.jdbc.menu.action;

public abstract class Action {
	protected final int id;
	protected final String description;
	
	protected Action(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public abstract boolean execute();
	
	public int getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
}
