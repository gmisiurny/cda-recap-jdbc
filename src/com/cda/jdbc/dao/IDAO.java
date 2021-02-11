package com.cda.jdbc.dao;

import java.util.List;

public interface IDAO<T> {
	T save(T o);
	List<T> getAll();
	void remove(String label);
	void update(String oldLabel, String newLabel);
}
