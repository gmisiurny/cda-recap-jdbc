package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreateCategory extends Action {
	private static final int ID = 1;
	private static final String DESC = "Créer une catégorie de pièce";
	
	protected CreateCategory() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la catégorie de la pièce à ajouter ?");
		// Ecrire le code pour persister la donnée si elle n'existe pas déjà
		return Boolean.TRUE;
	}
}
