package com.cda.jdbc.dao;

import com.cda.jdbc.data.Brand;

public interface IBrandDAO extends IDAO<Brand> {
	Brand findByName(String label);
}
