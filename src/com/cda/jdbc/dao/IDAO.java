package com.cda.jdbc.dao;

public interface IDAO<T> {
	T save(T o);
	T findByName(String label);
	void remove(String label);
	void update(String oldLabel, String newLabel);
}
