package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreateBrand extends Action {
	private static final int ID = 13;
	private static final String DESC = "Créer une marque de voiture";
	
	protected CreateBrand() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à créer ?");
		// Ecrire le code pour créer la marque de voiture
		return Boolean.TRUE;
	}
}
