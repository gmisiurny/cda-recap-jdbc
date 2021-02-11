package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class DeleteBrand extends Action {
	private static final int ID = 16;
	private static final String DESC = "Supprimer une marque de voiture";
	
	protected DeleteBrand() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le mod�le � supprimer ?");
		// Ecrire le code pour �diter la marque de voiture
		return Boolean.TRUE;
	}
}
