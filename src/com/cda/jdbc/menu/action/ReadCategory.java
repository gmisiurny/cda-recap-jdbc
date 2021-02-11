package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class ReadCategory extends Action {
	private static final int ID = 2;
	private static final String DESC = "Voir une cat�gorie de pi�ce";
	
	protected ReadCategory() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la cat�gorie de la pi�ce � consulter ?");
		// Ecrire le code pour voir la cat�gorie concern�e
		return Boolean.TRUE;
	}
}
