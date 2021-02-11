package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdateBrand extends Action {
	private static final int ID = 15;
	private static final String DESC = "Éditer une marque de voiture";
	
	protected UpdateBrand() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à éditer ?");
		// Ecrire le code pour éditer la marque de voiture
		return Boolean.TRUE;
	}
}
