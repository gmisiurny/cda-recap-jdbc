package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdatePiece extends Action {
	private static final int ID = 7;
	private static final String DESC = "Éditer une pièce";
	
	protected UpdatePiece() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la pièce à éditer ?");
		// Ecrire le code pour éditer la pièce
		return Boolean.TRUE;
	}
}
