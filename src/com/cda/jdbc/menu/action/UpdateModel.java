package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdateModel extends Action {
	private static final int ID = 11;
	private static final String DESC = "Éditer un modéle de voiture";
	
	protected UpdateModel() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à éditer ?");
		// Ecrire le code pour éditer la pièce
		return Boolean.TRUE;
	}
}
