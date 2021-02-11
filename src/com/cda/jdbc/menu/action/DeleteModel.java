package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class DeleteModel extends Action {
	private static final int ID = 12;
	private static final String DESC = "Supprimer un modéle de voiture";
	
	protected DeleteModel() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à supprimer ?");
		// Ecrire le code pour éditer la pièce
		return Boolean.TRUE;
	}
}
