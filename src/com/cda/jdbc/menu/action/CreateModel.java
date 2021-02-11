package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreateModel extends Action {
	private static final int ID = 9;
	private static final String DESC = "Créer un modéle de voiture";
	
	protected CreateModel() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à créer ?");
		// Ecrire le code pour éditer la pièce
		return Boolean.TRUE;
	}
}
