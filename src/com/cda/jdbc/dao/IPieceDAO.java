package com.cda.jdbc.dao;

import java.util.List;

import com.cda.jdbc.data.Piece;

public interface IPieceDAO extends IDAO<Piece> {
	List<Piece> getAllPiecePerCategory();
	boolean isInDatabase(String label);
	void updatePrice(String label, float price);
	void updateLabelAndPrice(String oldLabel, String newLabel, float price);
	void listAvailablePiecePerModel(String export);
}
