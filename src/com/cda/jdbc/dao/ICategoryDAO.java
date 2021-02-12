package com.cda.jdbc.dao;

import java.util.List;

import com.cda.jdbc.data.Category;

public interface ICategoryDAO extends IDAO<Category>{
	List<Category> getAll();
}
