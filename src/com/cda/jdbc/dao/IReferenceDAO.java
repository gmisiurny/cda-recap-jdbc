package com.cda.jdbc.dao;

import com.cda.jdbc.data.Reference;

public interface IReferenceDAO extends IDAO<Reference> {
	boolean isInDatabase(String label);
}
