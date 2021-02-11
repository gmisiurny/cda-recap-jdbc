package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreatePiece extends Action {
	private static final int ID = 5;
	private static final String DESC = "Créer une pièce";
	
	protected CreatePiece() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la pièce à ajouter ?");
		// Ecrire le code pour persister la donnée si elle n'existe pas déjà
		return Boolean.TRUE;
	}
}
