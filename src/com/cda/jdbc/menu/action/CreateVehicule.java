package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreateVehicule extends Action {
	private static final int ID = 17;
	private static final String DESC = "Créer un véhicule";
	
	protected CreateVehicule() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le véhicule à créer ?");
		// Ecrire le code pour créer
		return Boolean.TRUE;
	}
}
