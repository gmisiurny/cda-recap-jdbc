package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdateCategory extends Action {
	private static final int ID = 3;
	private static final String DESC = "Éditer une catégorie de pièce";
	
	protected UpdateCategory() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la catégorie de la pièce à consulter ?");
		// Ecrire le code pour éditer la catégorie concernée
		return Boolean.TRUE;
	}
}
