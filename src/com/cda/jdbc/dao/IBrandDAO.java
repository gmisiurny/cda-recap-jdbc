package com.cda.jdbc.dao;

import java.util.List;

import com.cda.jdbc.data.Brand;

public interface IBrandDAO extends IDAO<Brand> {
	List<Brand> getAll();
}
