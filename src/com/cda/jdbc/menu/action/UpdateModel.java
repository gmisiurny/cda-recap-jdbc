package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdateModel extends Action {
	private static final int ID = 11;
	private static final String DESC = "�diter un mod�le de voiture";
	
	protected UpdateModel() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le mod�le � �diter ?");
		// Ecrire le code pour �diter la pi�ce
		return Boolean.TRUE;
	}
}
