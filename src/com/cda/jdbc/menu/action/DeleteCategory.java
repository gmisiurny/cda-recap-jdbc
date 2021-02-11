package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class DeleteCategory extends Action {
	private static final int ID = 4;
	private static final String DESC = "Supprimer une cat�gorie de pi�ce";
	
	protected DeleteCategory() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la cat�gorie de la pi�ce � supprimer ?");
		// Ecrire le code pour supprimer la cat�gorie concern�e
		return Boolean.TRUE;
	}
}
