package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class ReadVehicule extends Action {
	private static final int ID = 17;
	private static final String DESC = "Consulter un v�hicule";
	
	protected ReadVehicule() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le v�hicule � consulter ?");
		// Ecrire le code pour consulter le v�hicule
		return Boolean.TRUE;
	}
}
