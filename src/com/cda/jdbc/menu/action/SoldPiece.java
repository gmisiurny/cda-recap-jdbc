package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class SoldPiece extends Action {
	private static final int ID = 20;
	private static final String DESC = "Vendre une pi�ce";
	
	protected SoldPiece() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Quelle pi�ce voulez-vous vendre ?");
		// Ecrire le code pour vendre la pi�ce concern�es
		return Boolean.TRUE;
	}
}
