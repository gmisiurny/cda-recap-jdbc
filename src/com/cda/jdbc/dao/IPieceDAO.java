package com.cda.jdbc.dao;

import java.util.List;

import com.cda.jdbc.data.Piece;

public interface IPieceDAO extends IDAO<Piece> {
	List<Piece> getAllPiecePerCategory();
	boolean isInDatabase(String label);
}
