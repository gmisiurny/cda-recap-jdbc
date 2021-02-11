package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class ReadPiece extends Action {
	private static final int ID = 6;
	private static final String DESC = "Voir une pièce";
	
	protected ReadPiece() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la pièce à consulter ?");
		// Ecrire le code pour consulter la pièce
		return Boolean.TRUE;
	}
}
