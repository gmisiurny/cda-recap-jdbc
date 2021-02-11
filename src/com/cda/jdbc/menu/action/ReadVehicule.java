package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class ReadVehicule extends Action {
	private static final int ID = 17;
	private static final String DESC = "Consulter un véhicule";
	
	protected ReadVehicule() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le véhicule à consulter ?");
		// Ecrire le code pour consulter le véhicule
		return Boolean.TRUE;
	}
}
