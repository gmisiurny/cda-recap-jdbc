package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class ReadCategory extends Action {
	private static final int ID = 2;
	private static final String DESC = "Voir une catégorie de pièce";
	
	protected ReadCategory() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la catégorie de la pièce à consulter ?");
		// Ecrire le code pour voir la catégorie concernée
		return Boolean.TRUE;
	}
}
