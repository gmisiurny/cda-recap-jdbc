package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class DeletePiece extends Action {
	private static final int ID = 8;
	private static final String DESC = "Supprimer une pi�ce";
	
	protected DeletePiece() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la pi�ce � Supprimer ?");
		// Ecrire le code pour �diter la pi�ce
		return Boolean.TRUE;
	}
}
