package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreateBrand extends Action {
	private static final int ID = 13;
	private static final String DESC = "Cr�er une marque de voiture";
	
	protected CreateBrand() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le mod�le � cr�er ?");
		// Ecrire le code pour cr�er la marque de voiture
		return Boolean.TRUE;
	}
}
