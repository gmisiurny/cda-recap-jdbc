package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdateVehicule extends Action {
	private static final int ID = 18;
	private static final String DESC = "Éditer un véhicule";
	
	protected UpdateVehicule() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le véhicule à éditer ?");
		// Ecrire le code pour éditer le véhicule
		return Boolean.TRUE;
	}
}
