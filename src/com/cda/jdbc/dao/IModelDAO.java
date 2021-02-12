package com.cda.jdbc.dao;

import java.util.List;
import com.cda.jdbc.data.Model;

public interface IModelDAO extends IDAO<Model> {
	List<Model> getAll();
}
