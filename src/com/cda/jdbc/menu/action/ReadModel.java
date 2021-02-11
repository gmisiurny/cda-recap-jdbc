package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class ReadModel extends Action{
	private static final int ID = 10;
	private static final String DESC = "Voir un mod�le de voiture";
	
	protected ReadModel() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le mod�le � consulter ?");
		// Ecrire le code pour consulter la pi�ce
		return Boolean.TRUE;
	}
}
