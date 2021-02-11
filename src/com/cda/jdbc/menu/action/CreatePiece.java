package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreatePiece extends Action {
	private static final int ID = 5;
	private static final String DESC = "Cr�er une pi�ce";
	
	protected CreatePiece() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la pi�ce � ajouter ?");
		// Ecrire le code pour persister la donn�e si elle n'existe pas d�j�
		return Boolean.TRUE;
	}
}
