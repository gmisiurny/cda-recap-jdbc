package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdateBrand extends Action {
	private static final int ID = 15;
	private static final String DESC = "�diter une marque de voiture";
	
	protected UpdateBrand() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le mod�le � �diter ?");
		// Ecrire le code pour �diter la marque de voiture
		return Boolean.TRUE;
	}
}
