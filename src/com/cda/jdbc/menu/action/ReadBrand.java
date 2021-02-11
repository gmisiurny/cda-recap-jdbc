package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class ReadBrand extends Action {
	private static final int ID = 14;
	private static final String DESC = "Consulter une marque de voiture";
	
	protected ReadBrand() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à consulter ?");
		// Ecrire le code pour consulter la marque de voiture
		return Boolean.TRUE;
	}
}
