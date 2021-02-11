package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class SoldPiece extends Action {
	private static final int ID = 20;
	private static final String DESC = "Vendre une pièce";
	
	protected SoldPiece() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Quelle pièce voulez-vous vendre ?");
		// Ecrire le code pour vendre la pièce concernées
		return Boolean.TRUE;
	}
}
