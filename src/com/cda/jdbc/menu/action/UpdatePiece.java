package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdatePiece extends Action {
	private static final int ID = 7;
	private static final String DESC = "�diter une pi�ce";
	
	protected UpdatePiece() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la pi�ce � �diter ?");
		// Ecrire le code pour �diter la pi�ce
		return Boolean.TRUE;
	}
}
