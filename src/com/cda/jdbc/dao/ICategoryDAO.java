package com.cda.jdbc.dao;

import com.cda.jdbc.data.Category;

public interface ICategoryDAO extends IDAO<Category>{
	Category findByName(String label);
}
