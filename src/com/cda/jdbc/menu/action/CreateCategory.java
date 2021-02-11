package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreateCategory extends Action {
	private static final int ID = 1;
	private static final String DESC = "Cr�er une cat�gorie de pi�ce";
	
	protected CreateCategory() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la cat�gorie de la pi�ce � ajouter ?");
		// Ecrire le code pour persister la donn�e si elle n'existe pas d�j�
		return Boolean.TRUE;
	}
}
