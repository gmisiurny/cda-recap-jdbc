package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdateCategory extends Action {
	private static final int ID = 3;
	private static final String DESC = "�diter une cat�gorie de pi�ce";
	
	protected UpdateCategory() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la cat�gorie de la pi�ce � consulter ?");
		// Ecrire le code pour �diter la cat�gorie concern�e
		return Boolean.TRUE;
	}
}
