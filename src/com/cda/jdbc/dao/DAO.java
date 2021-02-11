package com.cda.jdbc.dao;

import java.util.List;

public interface DAO<T> {
	T save(T o);
	List<T> getAll();
	void remove(String nomOuID);
	void update(String nomOuID);
}