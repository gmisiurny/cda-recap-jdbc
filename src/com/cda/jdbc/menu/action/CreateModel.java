package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreateModel extends Action {
	private static final int ID = 9;
	private static final String DESC = "Cr�er un mod�le de voiture";
	
	protected CreateModel() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le mod�le � cr�er ?");
		// Ecrire le code pour �diter la pi�ce
		return Boolean.TRUE;
	}
}
